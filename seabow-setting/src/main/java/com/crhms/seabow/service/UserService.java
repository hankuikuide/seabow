package com.crhms.seabow.service;

import com.crhms.seabow.model.Permission;
import com.crhms.seabow.model.Role;
import com.crhms.seabow.model.User;
import com.crhms.seabow.repository.UserRepository;
import com.crhms.seabow.security.JwtUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private static final String encryptSalt = "F12839WhsnnEV$#23b";

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private StringRedisTemplate redisTemplate;

    public User findUserById(long id) {

        return userRepository.getOne(id);

    }

    public User findByUsername(String name) {

        return userRepository.findByName(name);
    }

    /**
     * 保存user登录信息，返回token
     * @param username
     */
    public String generateJwtToken(String username) {
        String salt = "F12839WhsnnEV$#23b";//JwtUtils.generateSalt();

        return JwtUtils.sign(username, salt, 3600 * 12); //生成jwt token，设置过期时间为1小时
    }

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    public User getJwtTokenInfo(String username) {
        /**
         * @todo 从数据库或者缓存中取出jwt token生成时用的salt
         * salt = redisTemplate.opsForValue().get("token:"+username);
         */
        User user = getUserInfo(username);
        return user;
    }

    /**
     * 清除token信息
     * @param username 登录用户名
     */
    public void deleteLoginInfo(String username) {
        /**
         * @todo 删除数据库或者缓存中保存的salt
         * redisTemplate.delete("token:"+username);
         */

    }

    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     * @param userName
     * @return
     */
    public User getUserInfo(String userName) {

/*        User user = new User();
        user.setId(1L);
        user.setName("admin");
        user.setEncryptPwd(new Sha256Hash("123456", encryptSalt).toHex());*/
        return userRepository.findByName(userName);
    }

    /**
     * 获取用户角色列表，强烈建议从缓存中获取
     * @param userId
     * @return
     */
    public List<Role> getUserRoles(Long userId){
        User user = userRepository.getOne(userId);

        return user.getRoles();
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users;
    }

    public User addUser(User user) {

        String encryptPwd =new Sha256Hash(user.getPassword(), encryptSalt).toHex();
        user.setEncryptPwd(encryptPwd);
        user.setSalt(encryptSalt);
        user.setCreateDate(new Date());
        List<Permission> permissions = new ArrayList<>();
        Permission permission = new Permission();
        permission.setPermissiom("add");
        permissions.add(permission);

        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setRoleName("admin");
        role.setPermission(permissions);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

}
