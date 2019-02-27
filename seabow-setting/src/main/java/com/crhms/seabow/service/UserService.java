package com.crhms.seabow.service;

import com.crhms.seabow.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUserById(String id) {

        User user = new User();
        user.setPassword("string");

        return user;
    }

    public User findByUsername(String name) {
        User user = new User();
        return user;
    }

    public User getJwtTokenInfo(String name) {
        return  new User();
    }
}
