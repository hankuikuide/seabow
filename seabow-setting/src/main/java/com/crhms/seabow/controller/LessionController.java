package com.crhms.seabow.controller;

import com.crhms.seabow.Entity.Book;
import com.crhms.seabow.Entity.Lession;
import com.crhms.seabow.service.BookService;
import com.crhms.seabow.service.LessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lession")
public class LessionController {

    @Autowired
    private LessionService lessionService;

    @RequestMapping("/getLessions")
    public List<Lession> getLessions() {
        return  lessionService.getLessions();
    }
}
