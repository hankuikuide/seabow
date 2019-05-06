package com.crhms.seabow.controller;

import com.crhms.seabow.Entity.Log;
import com.crhms.seabow.interceptor.SystemControllerLog;
import com.crhms.seabow.model.Bill;
import com.crhms.seabow.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @SystemControllerLog(description = "记录日志")
    @GetMapping("")
    public List<Log> findAll() {

        return logService.GetLogs();
    }
}
