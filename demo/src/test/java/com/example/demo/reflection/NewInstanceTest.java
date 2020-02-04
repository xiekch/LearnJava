package com.example.demo.reflection;

import java.lang.reflect.Modifier;

import com.example.demo.annotation.Person;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
// import java.util.Random;

public class NewInstanceTest {
    @Test
    public void test1() {
        String cp = "";
        for (int i = 0; i < 10; i++) {
            int num = new java.util.Random().nextInt(3);
            switch (num) {
            case 0:
                cp = "java.util.Date";
                break;
            case 1:
                cp = "java.util.Random";
                break;
            case 2:
                cp = "java.lang.Object";
                break;
            }
            try {
                newInstance(cp);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void newInstance(String cp) throws Exception {
        Class cl = Class.forName(cp);
        Object obj = cl.getConstructor().newInstance();
        System.out.println(obj);
    }

    @Test
    public void test2() {
        Class cl = Person.class;
        // public fields, including super class
        Field[] fields = cl.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println();
        // all own fields, excluding super class
        fields = cl.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }

    }

    @Test
    public void test3() {
        Class cl = Person.class;
        // all own fields, excluding super class
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            System.out.print(Modifier.toString(f.getModifiers()) + '\t');
            System.out.print(f.getType().getName() + '\t');
            System.out.print(f.getName());
            System.out.println();
        }

    }

}