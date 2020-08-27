package com.crhms.seabow.security;

import com.alibaba.fastjson.JSONObject;
import com.crhms.seabow.model.User;
import com.crhms.seabow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtAuthFilter extends AuthenticatingFilter {

    private static final long TOKEN_REFRESH_INTERVAL = 24 * 60 * 60;
    private UserService userService;

    public JwtAuthFilter(UserService userService) {
        this.userService = userService;
        System.out.println("...................JwtAuthFilter...................");
        this.setLoginUrl("/login");
    }

    /**
     * 父类会在请求进入拦截器后调用该方法，返回true则继续，返回false则会调用onAccessDenied()。这里在不通过时，还调用了isPermissive()方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("..................................isAccessAllowed.................................");
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {

            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { //not found any token
            HttpServletRequest re = (HttpServletRequest)request;
            log.info(re.getRequestURI());

            log.error("not found any token");
        } catch (Exception e) {
            log.error("Error occurs when login", e);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro。这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        System.out.println("..................................createToken.................................");
        String jwtToken = getAuthzHeader(servletRequest);
        if (StringUtils.isNotBlank(jwtToken)) {
            if (!JwtUtils.isTokenExpired(jwtToken)) {
                return new JWTToken(jwtToken);
            } else {
                throw new AuthenticationException("createToken token过期，请重新登录");
            }
        }

        return null;
    }

    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setStatus(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION);

        fillCorsHeader(WebUtils.toHttp(servletRequest), httpResponse);

        throw new AuthenticationException("token认证失败");
        //return false;
    }

    /**
     * 如果Shiro Login认证成功，会进入该方法，等同于用户名密码登录成功，我们这里还判断了是否要刷新Token
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String newToken = null;
        if (token instanceof JWTToken) {
            JWTToken jwtToken = (JWTToken) token;
            User user = (User) subject.getPrincipal();
            boolean shouldRefresh = shouldTokenRefresh(JwtUtils.getIssuedAt(jwtToken.getToken()));
            if (shouldRefresh) {
                newToken = userService.generateJwtToken(user.getName());
            }
        }
        if (StringUtils.isNotBlank(newToken)) {
            httpResponse.setHeader("x-auth-token", newToken);
        }

        return true;
    }

    /**
     * 如果调用shiro的login认证失败，会回调这个方法，这里我们什么都不做，因为逻辑放到了onAccessDenied（）中。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("Validate token fail, token:{}, error:{}", token.toString(), e.getMessage());
        return false;
    }

    protected boolean shouldTokenRefresh(Date issueAt) {

        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(TOKEN_REFRESH_INTERVAL).isAfter(issueTime);
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String header = httpRequest.getHeader("x-auth-token");
        return StringUtils.removeStart(header, "Bearer ");
    }

    protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    }
}
