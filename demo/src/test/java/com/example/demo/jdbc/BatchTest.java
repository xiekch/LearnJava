package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.util.JDBCUtils;

import org.junit.jupiter.api.Test;

public class BatchTest {
    @Test
    public void test1() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            long start = System.currentTimeMillis();
            String sql = "insert into temp(name) values(?)";
            // prepareStatement can improve performance greatly
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 500; i++) {
                prepareStatement.setObject(1, "name_" + i);
                prepareStatement.executeUpdate();
            }
            long end = System.currentTimeMillis();
            System.out.println("time: " + (end - start) + " ms");
            // time: 1095 ms
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, prepareStatement);
        }
    }

    @Test
    public void test2() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            long start = System.currentTimeMillis();
            String sql = "insert into temp(name) values(?)";
            // prepareStatement can improve performance greatly
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 500; i < 1000; i++) {
                prepareStatement.setObject(1, "name_" + i);
                prepareStatement.addBatch();
                if (i % 100 == 0 || i == 999) {
                    prepareStatement.executeBatch();
                    prepareStatement.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("time: " + (end - start) + " ms");
            // time: 55 ms
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, prepareStatement);
        }
    }

    @Test
    public void test3() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            long start = System.currentTimeMillis();
            String sql = "insert into temp(name) values(?)";
            // prepareStatement can improve performance greatly
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 500; i < 1000; i++) {
                prepareStatement.setObject(1, "name_" + i);
                prepareStatement.addBatch();
                if (i % 100 == 0 || i == 999) {
                    prepareStatement.executeBatch();
                    prepareStatement.clearBatch();
                }
            }
            connection.commit();
            long end = System.currentTimeMillis();
            System.out.println("time: " + (end - start) + " ms");
            // time: 48 ms
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, prepareStatement);
        }
    }
}