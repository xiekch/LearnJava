package com.example.demo.annotation;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanTest{
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("annotationBean.xml");
        UserController userController=(UserController) ac.getBean("userController");
        System.out.println(userController);

        User user=(User)ac.getBean("user");
        System.out.println(user);
    }
}