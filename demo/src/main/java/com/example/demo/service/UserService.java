package com.example.demo.service;

import com.example.demo.dao.UserDaoImplTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

    @Autowired
    private UserDaoImplTemplate userDao;

    @Transactional
    public void transfer(int from, int to, int amount) {
        userDao.updateBalance(to, amount);
        userDao.updateBalance(from, -amount);
        System.out.println("success");
    }
}