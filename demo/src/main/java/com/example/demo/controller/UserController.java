package com.example.demo.controller;

import com.example.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private User user;

    @Override
    public String toString() {
        return "userController: " + this.user;
    }
}