package com.example.demo.mybatis.dao;

import java.util.List;

import com.example.demo.model.User;

public interface UserDao {
    public void insert(User user);

    public void deleteById(int id);

    public void update(User user);

    public User getUserById(int id);

    public List<User> getAll();

    public long getCount();
}