package com.example.demo.annotation.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Qualifier("bookdao2")
    @Autowired
    private BookDao bookDao;

    @Override
    public String toString() {
        return super.toString() + " " + bookDao;
    }
}