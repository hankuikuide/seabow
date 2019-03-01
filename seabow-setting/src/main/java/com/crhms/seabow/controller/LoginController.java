package com.crhms.seabow.controller;

import com.crhms.seabow.model.LoginDto;
import com.crhms.seabow.model.Role;
import com.crhms.seabow.model.User;
import com.crhms.seabow.service.ILoginService;
import com.crhms.seabow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginInfo, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        try {
            //将用户请求参数封装后，直接提交给Shiro处理
            UsernamePasswordToken token = new UsernamePasswordToken(loginInfo.getUserName(), loginInfo.getPassword());
            subject.login(token);
            //Shiro认证通过后会将user信息放到subject内，生成token并返回
            User user = (User) subject.getPrincipal();
            String newToken = userService.generateJwtToken(user.getName());
            response.setHeader("x-auth-token", newToken);

            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            // 如果校验失败，shiro会抛出异常，返回客户端失败
            log.error("User {} login fail, Reason:{}", loginInfo.getUserName(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

  /*  @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null) {
            User user = (User)subject.getPrincipals().getPrimaryPrincipal();
            userService.deleteLoginInfo(user.getName());
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }*/

    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map map) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                map.get("username").toString(),
                map.get("password").toString());

        subject.login(usernamePasswordToken);
        return "login";
    }*/

    @RequestMapping(value ="/index",method = RequestMethod.POST)
    public String index() {
        return "index";
    }

/*    @RequestMapping(value ="/logout",method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }*/

    //错误页面展示
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    //数据初始化
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(){
        Map<String,Object> map =new HashedMap();
        map.put("username", "hkk");
        map.put("password", "hkk123");
        User user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }

    //角色初始化
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }

    //注解的使用
/*    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(){
        return "Create success!";
    }*/
}
