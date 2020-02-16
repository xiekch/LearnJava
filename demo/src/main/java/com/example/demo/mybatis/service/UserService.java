package com.example.demo.mybatis.service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.mybatis.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mybatis.userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.getAll();
    }
}