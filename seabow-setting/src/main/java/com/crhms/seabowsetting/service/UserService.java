package com.crhms.seabowsetting.service;

import com.crhms.seabowinfrastructure.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findUserById(String id) {

        User user = new User();
        user.setId("string");
        user.setPassword("string");
        user.setPassword("string");

        return user;
    }

    public User findByUsername(User user) {

        return user;
    }
}
