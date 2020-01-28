package com.example.demo.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PreparedStatementTest {
    private Connection connection;

    {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beansFactory.xml");
        this.connection = (Connection) ac.getBean("connection");
        ac.close();
        System.out.println(this.connection);
    }

    @Test
    public void updateTest() {
        // ? placeholder
        String sql = "insert into user_table values(?,?,?);";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, "FF");
            preparedStatement.setString(2, "123456");
            preparedStatement.setInt(3, 2000);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}