package com.crhms.seabow.service;

import com.crhms.seabow.Entity.Book;
import com.crhms.seabow.Entity.Lession;
import com.crhms.seabow.repository.BookRepository;
import com.crhms.seabow.repository.LessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessionService {

    @Autowired
    private LessionRepository lessionRepository;

    public List<Lession> getLessions() {

        List<Lession> all = lessionRepository.findAll();

        return  all;
    }
}
