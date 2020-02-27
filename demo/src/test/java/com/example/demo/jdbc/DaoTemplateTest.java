package com.example.demo.jdbc;

import java.util.List;

import com.example.demo.dao.UserDaoImplTemplate;
import com.example.demo.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTemplateTest {
    private UserDaoImplTemplate userDaoImpl;
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc/beansJDBC.xml");
        userDaoImpl = applicationContext.getBean(UserDaoImplTemplate.class);
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    @Test
    public void testInsert() {
        User user = new User("Ken", "abcd", 1000);
        userDaoImpl.insert(user);
        System.out.println("success");
    }

    @Test
    public void testDelete() {
        int id = 6;
        userDaoImpl.deleteById(id);
        System.out.println("success");
    }

    @Test
    public void testUpdate() {
        User user = new User("Jennifer", "abcd", 5);
        userDaoImpl.update(user);
        System.out.println("success");
    }

    @Test
    public void testGetUserById() {
        int id = 5;
        User user = userDaoImpl.getUserById(id);
        System.out.println(user);
    }

    @Test
    public void testGetAll() {
        List<User> all = userDaoImpl.getAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testGetCount() {
        long count = userDaoImpl.getCount();
        System.out.println(count);
    }
}