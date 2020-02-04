package com.example.demo.reflection;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.util.Properties;

import com.example.demo.annotation.Person;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class ClassLoaderTest {
    // class loader app->platform->bootstrap(can't get)
    @Test
    public void test1() {
        ClassLoader loader1 = PersonTest.class.getClassLoader();
        out.println(loader1);
        ClassLoader loader2 = Person.class.getClassLoader();
        out.println(loader2);
        ClassLoader loader3 = loader1.getParent();
        out.println(loader3);
        out.println(loader1 == loader2);
        ClassLoader loader4 = loader3.getParent();
        out.println(loader4);

        ClassLoader loader5 = String.class.getClassLoader();
        out.println(loader5);
        out.println(loader5 == loader4);
    }

    @Test
    public void test2() throws Exception {
        Properties pro = new Properties();
        ClassPathResource classPathResource = new ClassPathResource("db.properties");
        FileInputStream file = new FileInputStream(classPathResource.getFile());
        pro.load(file);
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        out.println("user: " + user + ", password: " + password);
    }
}