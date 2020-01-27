package com.example.demo.aspect;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectTest {
    @Test
    public void test() {
        File directory = new File("");// 设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());// 获取标准的路径
            System.out.println(directory.getAbsolutePath());// 获取绝对路径
        } catch (Exception e) {
        }
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