package com.example.demo.annotation.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public BookService(@Qualifier("bookdao2") BookDao bookDao) {
        this.bookDao = bookDao;
        System.out.println("this is BookService's parameterd constructor");
    }

    @Override
    public String toString() {
        return super.toString() + " " + bookDao;
    }
}