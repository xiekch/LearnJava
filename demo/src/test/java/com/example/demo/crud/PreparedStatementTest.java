package com.example.demo.crud;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.User;
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
        String sql = "delete from user_table where name=?";
        JDBCUtils.update(sql, "EE");
    }

    @Test
    public void queryTest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from user_table where name =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "BB");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                String password = resultSet.getString(2);
                User user = new User(name, password);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }
    }

    @Test
    public void queryTest2() {
        String sql = "select name,password from user_table where name=?";
        User user = queryUser(sql, "AA");
        System.out.println(user);
    }

    public static User queryUser(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                User user = new User();
                for (int i = 0; i < columnCount; i++) {
                    Object obj = resultSet.getObject(i + 1);
                    // get alias of column
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Field field = User.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(user, obj);
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement);
        }

        return null;
    }

    @Test
    public void queryTest3() {
        String sql = "select name,password from user_table where name=?";
        String name = "AA";
        User user = JDBCUtils.querySingle(User.class, sql, name);
        System.out.println(user);
    }

    @Test
    public void queryTest4() {
        String sql = "select name,password from user_table";
        List<User> list = JDBCUtils.queryList(User.class, sql);
        list.forEach(System.out::println);
    }
}