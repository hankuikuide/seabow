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

import java.util.List;
import java.util.stream.Collectors;

public class DbShiroRealm extends AuthorizingRealm {

    private static final String encryptSalt = "F12839WhsnnEV$#23b";

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
        if(user == null) {
            throw new AuthenticationException("用户名或者密码错误");
        }

        return new SimpleAuthenticationInfo(user, user.getEncryptPwd(), ByteSource.Util.bytes(encryptSalt), "dbRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        List<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toList());

        if(roles == null) {
            roles = userService.getUserRoles(user.getId());
            user.setRoles(roles);
        }
        if (roles != null) {
            for (String name : roleNames) {
                simpleAuthorizationInfo.addRole(name);
            }
        }


        return simpleAuthorizationInfo;
    }
}
