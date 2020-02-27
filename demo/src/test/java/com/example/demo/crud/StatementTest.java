package com.example.demo.crud;

import java.util.Scanner;

import com.example.demo.model.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StatementTest {

    private Connection connection;

    {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean/beansFactory.xml");
        this.connection = (Connection) ac.getBean("connection");
        ac.close();
    }

    // WARNING: sql injection in Statement
    // no sql injection in PreparedStatement
    @Test
    public void loginTest() throws Exception {
        System.out.println(this.connection);
        Scanner scanner = new Scanner(System.in);
        System.out.println("input your name");
        // String name = scanner.nextLine();
        String name = "Alice";
        System.out.println("input your password");
        // String password = scanner.nextLine();
        String password = "123456";
        String query = "select name,password from user_table where name='" + name + "' and password='" + password
                + "';";
        User returnUser = this.get(query, User.class);
        if (returnUser == null) {
            System.out.println("failed");
        } else {
            System.out.println("succeeded");
        }

        scanner.close();
    }

    public <T> T get(String query, Class<T> cl) throws Exception {
        T t = null;
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnNum = resultSetMetaData.getColumnCount();
        if (resultSet.next()) {
            t = cl.getDeclaredConstructor().newInstance();
            for (int i = 0; i < columnNum; i++) {
                String columnName = resultSetMetaData.getColumnLabel(i + 1);
                Object columnValue = resultSet.getObject(columnName);

                Field field = cl.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t, columnValue);
            }
        }
        return t;
    }

}