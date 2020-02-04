package com.example.demo.dao;

import java.sql.Connection;
import java.util.List;

import com.example.demo.model.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public void insert(Connection connection, User user) {
        String sql = "insert into user_table(name,password) values(?,?)";
        update(connection, sql, user.getName(), user.getPassword());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from user_table where id=?";
        update(connection, sql, id);

    }

    @Override
    public void update(Connection connection, User user) {
        String sql = "update user_table set name=?, password=? where id=?";
        update(connection, sql, user.getName(), user.getPassword(), user.getId());
    }

    @Override
    public User getUserById(Connection connection, int id) {
        String sql = "select name,password,id from user_table where id=?";
        return querySingle(connection, sql, id);
    }

    @Override
    public List<User> getAll(Connection connection) {
        String sql = "select name,password,id from user_table";
        return queryList(connection, sql);
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from user_table";
        return getValue(connection, sql);
    }

}