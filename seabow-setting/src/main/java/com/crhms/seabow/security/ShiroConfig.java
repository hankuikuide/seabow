package com.crhms.seabow.security;

import com.crhms.seabow.service.UserService;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 注册shiro的Filter，拦截请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager,UserService userService) throws Exception{
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager, userService).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);

        return filterRegistration;
    }

    /**
     * 初始化Authenticator
     */
    @Bean
    public Authenticator authenticator(UserService userService) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

/*    @Bean
    public SecurityManager securityManager(UserService userService) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
        return securityManager;

    }*/
    /**
     * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
     * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * 用于用户名密码登录时认证的realm
     */
    @Bean("dbRealm")
    public Realm dbShiroRealm(UserService userService) {
        DbShiroRealm myShiroRealm = new DbShiroRealm(userService);
        return myShiroRealm;
    }
    /**
     * 用于JWT token认证的realm
     */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm(UserService userService) {
        JWTShiroRealm myShiroRealm = new JWTShiroRealm(userService);
        return myShiroRealm;
    }

    /**
     * 设置过滤器
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, UserService userService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authcToken", createAuthFilter(userService));
        filterMap.put("anyRole", createRolesFilter());
        factoryBean.setFilters(filterMap);
        //filterChainDefinitionMap必须是LinkedHashMap因为它必须保证有序:
        Map<String,String> map = new LinkedHashMap<>();

        map.put("/**/*.*", "anon");
        map.put("/import/run", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("webjars/springfox-swagger-ui/**", "anon");
        map.put("/configuration/**", "anon");
        map.put("/common/**", "anon");
        // map.put("/bill/**", "anon");
        map.put("/mongo/**", "anon");
        map.put("/login", "anon");
        map.put("/addUser", "anon");
        //登出
        map.put("/logout","logout");
        //对所有用户认证
        //对所有用户认证
        map.put("/bill/createBill","authcToken,anyRole[add]");
        map.put("/**","authcToken");

        factoryBean.setFilterChainDefinitionMap(map);
        //factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }

//    @Bean
//    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//
//        //filterChainDefinitionMap必须是LinkedHashMap因为它必须保证有序:
//        Map<String,String> map = new LinkedHashMap<>();
//
//        map.put("/**/*.*", "anon");
//        map.put("/swagger-ui.html", "anon");
//        map.put("/swagger-resources", "anon");
//        map.put("/v2/api-docs", "anon");
//        map.put("webjars/springfox-swagger-ui/**", "anon");
//        map.put("/configuration/**", "anon");
//        map.put("/common/**", "anon");
//        // map.put("/bill/**", "anon");
//        map.put("/mongo/**", "anon");
//        map.put("/login", "anon");
//        map.put("/addUser", "anon");
//        //登出
//        map.put("/logout","logout");
//        //对所有用户认证
//        map.put("/**","authc");
//
//        chainDefinition.addPathDefinitions(map);
//
//        return chainDefinition;
//    }

    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected JwtAuthFilter createAuthFilter(UserService userService){
        return new JwtAuthFilter(userService);
    }
    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected AnyRolesAuthorizationFilter createRolesFilter(){
        return new AnyRolesAuthorizationFilter();
    }

}