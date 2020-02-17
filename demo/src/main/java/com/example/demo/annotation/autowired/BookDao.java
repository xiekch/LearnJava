package com.example.demo.annotation.autowired;

import org.springframework.stereotype.Component;

@Component
public class BookDao {
    private int id;

    BookDao() {
    }

    BookDao(int id) {
        this.id = id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id;
    }
}