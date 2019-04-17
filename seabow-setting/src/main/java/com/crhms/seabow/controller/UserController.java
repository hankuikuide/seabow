package com.crhms.seabow.controller;

import com.crhms.seabow.model.User;
import com.crhms.seabow.model.UserDto;
import com.crhms.seabow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUsers")
    public Map getAllUsers() {
        Map<String, Object> map = new HashMap();
        log.info("...........getAllUsers...........");
        List<User> users = userService.getAllUsers();

        map.put("total", users.size());
        map.put("list", users);
        return  map;
    }


    @PostMapping(value = "/addUser")
    public String addUser(@RequestBody UserDto userDto) {
        log.info(userDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        userService.addUser(user);
        Map<String, Object> map = new HashMap<>();
        return "ok";
    }
}
