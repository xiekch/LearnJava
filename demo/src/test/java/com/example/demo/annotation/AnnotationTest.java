package com.example.demo.annotation;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;

public class AnnotationTest {
    @Test
    public static void test1() {
        @SuppressWarnings("unused")
        int num = 1;
    }

    @Test
    public static void test2() {
        Class cl = Student.class;
        Annotation[] anns = cl.getAnnotations();
        for (Annotation an : anns) {
            System.out.println(an);
        }
    }

}
