package com.crhms.seabow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j

public class SeabowUserApplication {

    public static void main(String[] args) {

        SpringApplication.run(SeabowUserApplication.class, args);

        log.info("user 项目启动成功!！！！！！！！！");

    }

}

