package com.example.demo.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class DriverTest {
    @Test
    public void test1() throws SQLException, IOException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/gc";
        Properties properties = new Properties();
        InputStream in = this.getClass().getResourceAsStream("/jdbc/jdbc.properties");

        // FileInputStream in = new FileInputStream("src/main/resources/jdbc/jdbc.properties");
        // the following doesn't work
        // FileInputStream in = new FileInputStream("jdbc/jdbc.properties");
        properties.load(in);
        Connection connection = driver.connect(url, properties);
        System.out.println(connection);
    }

    // reflection
    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("jdbc/jdbc.properties");
        File file = resource.getFile();
        FileInputStream in = new FileInputStream(file);
        properties.load(in);

        Class cl = Class.forName(properties.getProperty("driver"));
        Driver driver = (Driver) cl.getDeclaredConstructor().newInstance();
        String url = properties.getProperty("url");
        // InputStream in = this.getClass().getResourceAsStream("/jdbc/jdbc.properties");
        Connection connection = driver.connect(url, properties);
        System.out.println(connection);
    }

    @Test
    public void test3() throws Exception {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("jdbc/jdbc.properties");
        File file = resource.getFile();
        FileInputStream in = new FileInputStream(file);
        properties.load(in);

        Class cl = Class.forName(properties.getProperty("driver"));
        Driver driver = (Driver) cl.getDeclaredConstructor().newInstance();
        String url = properties.getProperty("url");

        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, properties);
        System.out.println(connection);
    }

    @Test
    public void test4() throws Exception {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("jdbc/jdbc.properties");
        File file = resource.getFile();
        FileInputStream in = new FileInputStream(file);
        properties.load(in);

        String url = properties.getProperty("url");
        // only need to load driver.class
        // the code in com.mysql.cj.jdbc.Driver
        // static {
        // try {
        // java.sql.DriverManager.registerDriver(new Driver());
        // } catch (SQLException E) {
        // throw new RuntimeException("Can't register driver!");
        // }
        // }

        Class.forName(properties.getProperty("driver"));

        Connection connection = DriverManager.getConnection(url, properties);
        System.out.println(connection);
    }
}