package com.crhms.seabow.controller;

import com.crhms.seabow.interceptor.SystemControllerLog;
import com.crhms.seabow.model.User;
import com.crhms.seabow.model.UserDto;
import com.crhms.seabow.model.UserSearch;
import com.crhms.seabow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @SystemControllerLog(description = "获取用户信息")
    @PostMapping(value = "/getAllUsers")
    public Map getAllUsers(@RequestBody UserSearch search) {
        Map<String, Object> map = new HashMap();
        Page<User> users = userService.getAllUsers(search);

        map.put("total", users.getTotalElements());
        map.put("list", users.getContent());
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

    @PostMapping(value = "/editUser")
    public String editUser(@RequestBody UserDto userDto) {
        log.info(userDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        userService.editUser(user);
        return "ok";
    }
}
