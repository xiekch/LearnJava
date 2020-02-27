package com.example.demo.annotation;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanTest {
    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean/annotationBean.xml");
        UserController userController = (UserController) ac.getBean("userController");
        System.out.println(userController);

        User user = (User) ac.getBean("user");
        System.out.println(user);
        ((ConfigurableApplicationContext) ac).close();
    }
}