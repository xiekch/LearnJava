package com.example.demo.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.model.User;

public interface UserMapper {
    public int insert(User user);

    public int deleteById(int id);

    public int update(User user);

    public User getUserById(int id);

    public List<User> getAll();

    public long getCount();

    public User getUserByIdAndPasswordMap(Map<String, Object> map);
}