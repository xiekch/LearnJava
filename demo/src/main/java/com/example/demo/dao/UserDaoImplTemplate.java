package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImplTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(User user) {
        String sql = "insert into user_table(name,password) values(?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getPassword());
    }

    public void deleteById(int id) {
        String sql = "delete from user_table where id=?";
        jdbcTemplate.update(sql, id);
    }

    public void update(User user) {
        String sql = "update user_table set name=?, password=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getId());
    }

    public User getUserById(int id) {
        String sql = "select name,password,id from user_table where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    public List<User> getAll() {
        String sql = "select name,password,id from user_table where id <?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper, 5);
    }

    public long getCount() {
        String sql = "select count(*) from user_table";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}