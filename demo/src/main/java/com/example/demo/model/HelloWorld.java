package com.example.demo.model;

public class HelloWorld {
    private String name;

    public HelloWorld() {
        System.out.println("helloworld's constructor");
    }

    public void setname(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello " + this.name);
    }
}