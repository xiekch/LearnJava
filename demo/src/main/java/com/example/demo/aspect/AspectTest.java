package com.example.demo.aspect;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ca = new ClassPathXmlApplicationContext("aspectBeans.xml");
        Calculator calculator = (Calculator) ca.getBean("calculator");
        Calculator calculator2 = (Calculator) ca.getBean("calculator");
        System.out.println(calculator.getClass().getName());
        System.out.println(calculator == calculator2);
        try {
            System.out.println(calculator.add(2, 3));
            System.out.println(calculator.mul(2, 3));
            System.out.println(calculator.div(2, 0));
        } catch (Exception e) {
            // e.printStackTrace();
        }
        ca.close();
    }
}