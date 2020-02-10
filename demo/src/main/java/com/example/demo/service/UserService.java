package com.example.demo.service;

import com.example.demo.dao.UserDaoImplTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

    @Autowired
    private UserDaoImplTemplate userDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transfer(int from, int to, int amount) {
        userDao.updateBalance(to, amount);
        userDao.updateBalance(from, -amount);
        System.out.println("success");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 2)
    public void transferException(int from, int to, int amount) {
        userDao.updateBalance(to, amount);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDao.updateBalance(from, -amount);
        System.out.println("success");
    }

}