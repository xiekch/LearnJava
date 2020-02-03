package com.example.demo.dao;

import java.sql.Connection;
import java.util.List;

import com.example.demo.model.User;

public interface UserDao {
    public void insert(Connection connection, User user);

    public void deleteById(Connection connection, int id);

    public void update(Connection connection, User user);

    public User getUserById(Connection connection, int id);

    public List<User> getAll(Connection connection);

    public long getCount(Connection connection);
}