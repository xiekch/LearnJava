package com.example.demo.pool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.example.demo.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JDBCBeanTest {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc/beansJDBC.xml");
        dataSource = (DataSource) applicationContext.getBean("dataSource");
        jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    @Test
    public void testConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testUpdate() {
        String sql = "insert into user_table(name,password) values(?,?)";
        jdbcTemplate.update(sql, "Polo", "abcdef");
    }

    @Test
    public void testBatchUpdate() {
        String sql = "insert into user_table(name,password) values(?,?)";
        List<Object[]> args = new ArrayList<Object[]>();
        args.add(new Object[] { "Lee", "abcd" });
        args.add(new Object[] { "Gree", "abcd" });
        jdbcTemplate.batchUpdate(sql, args);
    }

    @Test
    public void testQueryObject() {
        String sql = "select name,password,id from user_table where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, 4);
        System.out.println(user);
    }

    @Test
    public void testCount() {
        String sql = "select count(*) from user_table";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(count);
    }
}