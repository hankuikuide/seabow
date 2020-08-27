package com.crhms.seabow.service;

import com.crhms.seabow.Entity.Book;
import com.crhms.seabow.Entity.Subtitle;
import com.crhms.seabow.repository.BookRepository;
import com.crhms.seabow.repository.SubtitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {

        List<Book> all = bookRepository.findAll();

        return  all;
    }
}
