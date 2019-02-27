package com.crhms.seabow.service;

import com.crhms.seabow.model.Permission;
import com.crhms.seabow.model.Role;
import com.crhms.seabow.model.User;
import com.crhms.seabow.repository.RoleRepository;
import com.crhms.seabow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LoginService implements ILoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword(map.get("password").toString());
        userRepository.save(user);
        return user;
    }

    @Override
    public Role addRole(Map<String, Object> map) {
        User user =userRepository.findById(Long.valueOf(map.get("userId").toString())).get();
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user);
        Permission permission1= new Permission();
        permission1.setPermissiom("create");
        permission1.setRole(role);
        Permission permission2= new Permission();
        permission2.setPermissiom("update");
        permission2.setRole(role);

        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermission(permissions);
        roleRepository.save(role);
        return role;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
