package com.example.demo.pool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.example.demo.dao.UserDaoImpl;
import com.example.demo.model.User;
import com.example.demo.util.C3P0Utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class C3P0Test {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Test
    public void test1() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        ClassPathResource classPathResource = new ClassPathResource("db.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(classPathResource.getFile()));
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("user"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        comboPooledDataSource.setInitialPoolSize(10);
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("config");
        Connection connection = comboPooledDataSource.getConnection();
        System.out.println(connection);
    }


    @Test
    public void testGetAll() {
        Connection connection = null;
        try {
            connection = C3P0Utils.getConnection();
            List<User> all = userDaoImpl.getAll(connection);
            all.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.closeResource(connection, null);
        }
    }

}