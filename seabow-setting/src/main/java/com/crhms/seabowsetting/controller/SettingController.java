package com.crhms.seabowsetting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingController {

    @GetMapping("/test")
    public String GetMessage(){
        return "OK!!!";
    }
}
