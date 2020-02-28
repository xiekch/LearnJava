package com.example.demo.springboot;

import com.example.demo.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringBootConfigTest {
    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ac;

    @Test
    public void testconfig() {
        Object bean = ac.getBean("car");
        System.out.println(bean);
        System.out.println(this.person);
    }
}