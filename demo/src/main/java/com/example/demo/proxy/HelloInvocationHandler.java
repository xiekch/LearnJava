package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloInvocationHandler implements InvocationHandler {
    private IHello delegate;

    public HelloInvocationHandler(IHello hello) {
        this.delegate = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (delegate == null)
            return null;
        try {
            System.out.println("begin");
            method.invoke(delegate, args);
            System.out.println("end");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
