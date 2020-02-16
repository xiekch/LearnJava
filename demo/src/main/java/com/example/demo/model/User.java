package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;
    private String password;
    private int id;
    private int balance;

    public User() {
        this.name = "Tom";
        this.password = "123456";
        this.id = 5;
        this.balance = 0;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, int balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public User(String name, String password, int balance, int id) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.id = id;
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
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "I am " + this.name + ". My id is " + this.id;
    }
}