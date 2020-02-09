package com.example.demo.annotation;

import com.example.demo.config.AnnotationConfig;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AnnotationConfigTest {
    @Autowired
    private User user;

    @Test
    public void testPerson() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Person person = (Person) ac.getBean("person");
        System.out.println(person);

        Person person2 = (Person) ac.getBean("person2");
        System.out.println(person2);
    }
    @Test

    public void testScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        UserController userController = (UserController) ac.getBean("userController");
        System.out.println(userController);
    }

    @Test
    public void TestAutowired() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        AnnotationConfigTest bean = ac.getBean(AnnotationConfigTest.class);
        System.out.println(bean);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "config: " + this.user;
    }
}