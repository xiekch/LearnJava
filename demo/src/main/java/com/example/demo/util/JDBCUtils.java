package com.example.demo.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class JDBCUtils {
    public static Connection getConnection() {
        Properties properties = new Properties();
        ClassPathResource resource = new ClassPathResource("db.properties");
        FileInputStream in = null;
        Connection connection = null;
        try {
            in = new FileInputStream(resource.getFile());
            properties.load(in);

            Class.forName(properties.getProperty("driver"));

            String url = properties.getProperty("url");
            connection = DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeResource(Connection connection, PreparedStatement preparedStatement) {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(connection, preparedStatement);
        }
    }
}