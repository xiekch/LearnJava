package com.example.demo.annotation.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {
    @Test
    public void testAutowired() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        BookService bean = ac.getBean(BookService.class);
        System.out.println(bean);
        ac.close();
    }


    @Test
    public void testMultiClasses() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutowiredConfig.class);
        BookService bean = ac.getBean(BookService.class);
        System.out.println(bean);
        ac.close();
    }
}