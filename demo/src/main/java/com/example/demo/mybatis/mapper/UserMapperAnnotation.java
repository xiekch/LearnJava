package com.example.demo.mybatis.mapper;

import java.util.List;

import com.example.demo.model.User;

import org.apache.ibatis.annotations.Select;

public interface UserMapperAnnotation {
    
    public void insert(User user);

    public void deleteById(int id);

    public void update(User user);

    @Select("select * from user_table where id = #{id}")
    public User getUserById(int id);

    public List<User> getAll();

    public long getCount();   
}