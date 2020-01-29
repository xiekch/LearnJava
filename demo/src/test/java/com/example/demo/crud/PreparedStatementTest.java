package com.example.demo.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.util.JDBCUtils;

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
    public void CreateTest() {
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

    @Test
    public void updateTest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update user_table set name=? where name =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "GG");
            preparedStatement.setObject(2, "DD");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }

    @Test
    public void deleteTest() {
        String sql="delete from user_table where name=?";
        JDBCUtils.update(sql, "EE");
    }
}