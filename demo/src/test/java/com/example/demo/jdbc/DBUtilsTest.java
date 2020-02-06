package com.example.demo.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.util.DruidUtils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

public class DBUtilsTest {
    @Test
    public void testQueryRunnerUpdate() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "insert into user_table (name,password,balance) values(?,?,?)";
            int count = queryRunner.update(connection, sql, "Dan", "abcdef", 1000);
            System.out.println((count != 0) ? "success" : "failure");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    public void testQueryRunnerQuery() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "select name,password from user_table where id=?";
            QueryRunner queryRunner = new QueryRunner();
            BeanHandler<User> beanHandler = new BeanHandler<>(User.class);
            User user = queryRunner.query(connection, sql, beanHandler, 1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    public void testQueryRunnerQueryList() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "select name,password from user_table where id<?";
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<User> beanHandler = new BeanListHandler<>(User.class);
            List<User> users = queryRunner.query(connection, sql, beanHandler, 3);
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    public void testQueryRunnerScalar() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "select count(*) from user_table";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler<Long> handler = new ScalarHandler<>();
            Long count = queryRunner.query(connection, sql, handler);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    public void testSelfDefinedHandler() {
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            String sql = "select name,password from user_table where id=?";
            QueryRunner queryRunner = new QueryRunner();
            ResultSetHandler<User> handler = new ResultSetHandler<User>() {

                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if (rs.next()) {
                        ResultSetMetaData metaData = rs.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        User user;
                        try {
                            user = User.class.getConstructor().newInstance();
                            for (int i = 0; i < columnCount; i++) {
                                String columnLabel = metaData.getColumnLabel(i + 1);
                                Field declaredField = User.class.getDeclaredField(columnLabel);
                                declaredField.setAccessible(true);
                                declaredField.set(user, rs.getObject(i + 1));
                            }
                            return user;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    return null;
                }
            };
            User user = queryRunner.query(connection, sql, handler, 4);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

}