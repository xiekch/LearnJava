package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.demo.util.JDBCUtils;

import org.junit.jupiter.api.Test;

public class TransactionTest {
    @Test
    public void test1() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            System.out.println(connection.getAutoCommit());
            String sql1 = "update user_table set balance = balance-100 where id=?";
            String sql2 = "update user_table set balance = balance+100 where id=?";
            JDBCUtils.update(connection, sql1, 1);
            System.out.println(10 / 0);
            JDBCUtils.update(connection, sql2, 2);
            connection.commit();
            System.out.println("transaction success");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("rollback");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}