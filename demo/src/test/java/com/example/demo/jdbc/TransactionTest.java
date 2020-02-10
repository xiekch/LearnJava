package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.demo.service.UserService;
import com.example.demo.util.JDBCUtils;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void testUpdateBalance() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.transfer(2, 3, 2000);
    }

    /**
     * 在用REQUIRES_NEW的时候，发现没有起作用。分析了一下，原因是
     * A方法（有事务）调用B方法（要独立新事务），如果两个方法写在同一个类里，
     * spring的事务会只处理能同一个。
     */
    @Test
    @Transactional
    public void testTransactionPropagation() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("transaction.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.transfer(2, 1, 1000);
        userService.transferException(4, 3, 1000);
        System.out.println("finished");
    }
}