package com.example.demo.jdbc;

import java.sql.Connection;
import java.util.List;

import com.example.demo.dao.UserDaoImpl;
import com.example.demo.model.User;
import com.example.demo.util.JDBCUtils;

import org.junit.jupiter.api.Test;

public class DaoTest {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Test
    public void testInsert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new User("Jennifer", "abcd", 0);
            userDaoImpl.insert(connection, user);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testDelete() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            int id = 6;
            userDaoImpl.deleteById(connection, id);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testUpdate() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new User("Jennifer", "abcd", 5);
            userDaoImpl.update(connection, user);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetUserById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            int id = 5;
            User user = userDaoImpl.getUserById(connection, id);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetAll() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            List<User> all = userDaoImpl.getAll(connection);
            all.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void testGetCount() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            long count = userDaoImpl.getCount(connection);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}