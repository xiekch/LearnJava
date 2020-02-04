package com.example.demo.reflection;
// cd .. ; javac -d . PersonTest.java

// java -cp . com.example.demo.reflection.PersonTest

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.example.demo.annotation.Person;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @Test
    public void test1() {
        assertEquals(0, 0);
        Person p1 = new Person("Ann", 11);
        out.println(p1.toString());
    }

    @Test
    public void test2() throws Exception {
        Class<Person> cl = Person.class;
        Constructor<Person> con = cl.getConstructor(String.class, int.class);
        Object obj = con.newInstance("Maria", 12);
        out.println(obj.toString());
        Person p = (Person) obj;
        Field age = cl.getDeclaredField("age");
        age.set(p, 10);
        out.println(p.toString());

        Method m = cl.getMethod("show");
        m.invoke(p);

        con = cl.getDeclaredConstructor(String.class);
        con.setAccessible(true);
        Object obj2 = con.newInstance("Joe");
        out.println(obj2);
        Field name = cl.getDeclaredField("name");
        name.setAccessible(true);
        name.set(obj2, "Neil");
        out.println(obj2);

        Method showNation = cl.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(obj2, "Brazil");
        out.println(nation);

    }

    @Test
    public void test3() throws Exception {
        // get a Class instance of a class
        // method 1
        Class<Person> cl1 = Person.class;
        out.println(cl1);
        Constructor<Person> con = cl1.getConstructor(String.class, int.class);
        Object obj = con.newInstance("Maria", 12);
        Person p = (Person) obj;
        out.println(p);

        // method 2
        Person p1 = new Person();
        Class cl2 = p1.getClass();
        out.println(cl2);

        // method 3 mainstream
        Class cl3 = Class.forName("com.example.demo.annotation.Person");
        out.println(cl3);

        out.println((cl1 == cl2) && (cl2 == cl3));

        // method 4
        Class cl4 = PersonTest.class.getClassLoader().loadClass("com.example.demo.annotation.Person");
        out.println(cl4);

        Person p3 = new Person();
        out.println(p3.getClass());
        out.println(p3.getClass().getClass());
        out.println(p3.getClass().getClass().getClass());
        out.println(p3.getClass().getClass().getClass().getClass());
        out.println((Class.class));
    }

    @Test
    public void test4() {
        int[] arr1 = new int[1];
        int[] arr2 = new int[2];
        String[] arr3 = new String[4];
        Class cl1 = arr1.getClass();
        Class cl2 = arr2.getClass();
        Class cl3 = arr3.getClass();
        out.println(cl1);
        out.println(cl3);
        out.println(cl1 == cl2);
    }

    @Test
    public void test5() throws Exception {
        out.println(1);
        Class<Person> per = Person.class;
        Person p1 = per.getConstructor().newInstance();
        out.println(p1);
    }
}
