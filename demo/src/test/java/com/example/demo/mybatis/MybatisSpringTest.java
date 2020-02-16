package com.example.demo.mybatis;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.mybatis.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisSpringTest {
    @Test
    public void testIntegration() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("mybatis/mybatis_spring.xml");
        UserService service = ac.getBean(UserService.class);
        List<User> users = service.getUsers();
        users.forEach(System.out::println);
        ac.close();
    }
}