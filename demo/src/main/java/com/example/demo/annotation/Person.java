package com.example.demo.annotation;

import org.springframework.beans.factory.annotation.Value;

@MyAnnotation("hello")
public class Person {
    // @Value cannot be changed by set method
    // maybe it is implemented by injecting when returned
    @Value("${person.name}")
    private String name;
    // @Value("40")
    public int age;

    public Person() {
        System.out.println("Person()");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person()");
    }

    @SuppressWarnings("unused")
    private Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public void show() {
        System.out.println("hello");
    }

    @SuppressWarnings("unused")
    private String showNation(String nation) {
        System.out.println("I am from " + nation);
        return nation;
    }

    public String toString() {
        return "name: " + this.name + ", age: " + this.age;
    }

    public void init() {
        System.out.println("init person");
    }

    public void destroy() {
        System.out.println("destory person");
    }
}
