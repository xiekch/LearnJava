package com.example.demo.annotation;

@MyAnnotation("hello")
public class Person {
    private String name;
    public int age;

    public Person() {
        System.out.println("Person()");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

}