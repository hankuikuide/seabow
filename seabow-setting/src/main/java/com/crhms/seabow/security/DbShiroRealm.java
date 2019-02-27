package com.crhms.seabow.security;

import com.crhms.seabow.model.Permission;
import com.crhms.seabow.model.Role;
import com.crhms.seabow.model.User;
import com.crhms.seabow.service.ILoginService;
import com.crhms.seabow.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class DbShiroRealm extends AuthorizingRealm {

    private static final String encryptSalt = "F12839WhsnnEV$#23b";

    @Autowired
    private ILoginService loginService;

    @Autowired
    private UserService userService;

    public DbShiroRealm(UserService userService) {
        this.userService = userService;
        this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     *  这一步我们根据token给的用户名，去数据库查出加密过用户密码，然后把加密后的密码和盐值一起发给shiro，让它做比对
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userpasswordToken = (UsernamePasswordToken)token;
        String username = userpasswordToken.getUsername();
        User user = userService.findByUsername(username);
        if(user == null)
            throw new AuthenticationException("用户名或者密码错误");

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(encryptSalt), "dbRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    /*@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String name = (String)principalCollection.getPrimaryPrincipal();
        User user = loginService.findByName(name);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermission()) {
                simpleAuthorizationInfo.addStringPermission(permission.getPermissiom());
            }
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.findByName(name);
        if (user == null) {
            return null;
        } else {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }*/
}
