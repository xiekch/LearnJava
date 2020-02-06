package com.example.demo.pool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import com.example.demo.dao.UserDaoImpl;
import com.example.demo.model.User;
import com.example.demo.util.DruidUtils;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class DruidTest {
    private UserDaoImpl userDaoImpl = new UserDaoImpl();

    @Test
    public void test1() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("druid.properties");
        Properties properties = new Properties();
        properties.load(new FileInputStream(classPathResource.getFile()));
        DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testGetAll() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            List<User> all = userDaoImpl.getAll(connection);
            all.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtils.closeResource(connection, null);
        }
    }

}