package com.crhms.seabow.controller;

import com.crhms.seabow.Entity.Menu;
import com.crhms.seabow.model.MenuDto;
import com.crhms.seabow.model.User;
import com.crhms.seabow.model.UserDto;
import com.crhms.seabow.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 */
@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenus();
    }


    @PostMapping(value = "/addMenu")
    public String addMenu(@RequestBody MenuDto menuDto) {
        log.info(menuDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        Menu menu = modelMapper.map(menuDto, Menu.class);
        menuService.addMenu(menu);
        return "ok";
    }

    @PostMapping(value = "/editMenu")
    public String editMenu(@RequestBody MenuDto menuDto) {
        log.info(menuDto.toString());
        ModelMapper modelMapper = new ModelMapper();
        Menu menu = modelMapper.map(menuDto, Menu.class);
        menuService.editMenu(menu);
        return "ok";
    }
}