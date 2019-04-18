package com.crhms.seabow.service;

import com.crhms.seabow.Entity.Menu;
import com.crhms.seabow.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus(){
        return menuRepository.findAll();
    }

    public Menu addMenu(Menu menu) {

        menuRepository.save(menu);
        return menu;
    }

    public Menu editMenu(Menu u) {
        Menu menu = menuRepository.getOne(u.getId());
        menu.setCreateDate(new Date());
        menu.setPath(u.getPath());
        menu.setName(u.getName());
        menu.setUrl(u.getUrl());
        menu.setIconCls(u.getIconCls());
        menu.setParentId(u.getParentId());
        menu.setState(u.getState());
        menuRepository.save(menu);
        return menu;

    }
}
