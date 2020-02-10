package com.example.demo.annotation;

import com.example.demo.aspect.Logging;
import com.example.demo.config.AnnotationConfig;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AnnotationConfigTest {
    @Autowired
    private User user;

    private void printDefinitionNames(ApplicationContext ac) {
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

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
        printDefinitionNames(ac);
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

    @Test
    public void testScope() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Person user1 = (Person) ac.getBean("person2");
        Person user2 = (Person) ac.getBean("person2");

        System.out.println(user1 == user2);
    }

    @Test
    public void testLoad() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.out.println("container created");
        Person user1 = (Person) ac.getBean("person3");
        Person user2 = (Person) ac.getBean("person3");

        System.out.println(user1 == user2);
    }

    @Test
    public void testCondition() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Environment environment = ac.getEnvironment();
        String osname = environment.getProperty("os.name");
        System.out.println(osname);
    }

    @Test
    public void testImport() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        printDefinitionNames(ac);
        Logging logging = (Logging) ac.getBean("com.example.demo.aspect.Logging");
        System.out.println(logging);
    }

    @Override
    public String toString() {
        return "config: " + this.user;
    }
}