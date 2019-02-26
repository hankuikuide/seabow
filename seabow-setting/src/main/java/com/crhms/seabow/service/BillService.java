package com.crhms.seabow.service;

import com.crhms.seabow.repository.BillRepository;
import com.crhms.seabow.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;


    public void  CreateBill(Bill bill) {

        billRepository.save(bill);
        System.out.println("保存成功");
    }

    public List<Bill> FindAll() {

        System.out.println("查询成功");

       return billRepository.findAll();
    }
}
