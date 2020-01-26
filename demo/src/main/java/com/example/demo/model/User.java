package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;

    public User() {
        this.name = "Tom";
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "I am " + this.name;
    }
}