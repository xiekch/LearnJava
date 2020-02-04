package com.example.demo.proxy;

public class PersonHello implements IHello {

    public PersonHello() {
    }

    @Override
    public void sayHello() {
        System.out.println("a person says hello");
    }

    @Override
    public void sayBye() {
        System.out.println("a person says bye");
    }

}