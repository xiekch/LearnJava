package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;
    private String password;

    public User() {
        this.name = "Tom";
        this.password = "123456";
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "I am " + this.name;
    }
}