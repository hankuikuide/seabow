package com.crhms.seabow.service;

import com.crhms.seabow.Entity.Log;
import com.crhms.seabow.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public List<Log> GetLogs(){
        return logRepository.findAll();
    }

    public void save(Log log) {

        logRepository.save(log);
    }
}
