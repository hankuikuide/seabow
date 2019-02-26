package com.crhms.seabow.controller;

import com.crhms.seabow.model.Bill;
import com.crhms.seabow.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("/createBill")
    public boolean createBill(@RequestParam("hospitalId") String hospitalId, @RequestParam("hospitalName")String hospitalName) {

        Bill bill= new Bill();
        bill.setHospitalId(hospitalId);
        bill.setHospitalName(hospitalName);
        billService.CreateBill(bill);

        return true;
    }
    @GetMapping("/findAll")
    public List<Bill> findAll() {
        return billService.FindAll();
    }
}
