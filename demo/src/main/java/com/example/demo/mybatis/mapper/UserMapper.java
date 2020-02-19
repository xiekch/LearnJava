package com.example.demo.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.model.User;

import org.apache.ibatis.annotations.MapKey;

public interface UserMapper {
    public int insert(User user);

    public int deleteById(int id);

    public int update(User user);

    public User getUserById(int id);

    public List<User> getAll();

    public long getCount();

    public User getUserByIdAndPasswordMap(Map<String, Object> map);

    public Map<String, Object> getUserByIdReturnMap(int id);

    @MapKey("id")
    public Map<Integer,User> getAllReturnMap();
}