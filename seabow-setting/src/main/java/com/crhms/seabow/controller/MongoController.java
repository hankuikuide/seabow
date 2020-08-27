package com.crhms.seabow.controller;

import com.crhms.seabow.model.Bill;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoController {

    @Autowired
    //private MongoTemplate mongoTemplate;

    @GetMapping("/mongotest")
    public void add_mongo() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10; i++) {
            Bill bill = new Bill();
            bill.setHospitalName("HospitalName"+ i);
            bill.setHospitalId("HospitalId" +i);
            bill.setId(100000000 + Long.valueOf(i));

            bill.setCreateDate(new Date());
            bill.setExcuteDate(new Date());
            bill.setUpdateDate(new Date());

            bill.setDockerId(UUID.randomUUID().toString());
            bill.setDocterName(UUID.randomUUID().toString());
            bill.setPatientId(UUID.randomUUID().toString());
            bill.setPatientName(UUID.randomUUID().toString());

            //mongoTemplate.save(bill);
        }
        Long consumeTime = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        log.info("耗时：" + consumeTime + "(毫秒).");


    }

}
