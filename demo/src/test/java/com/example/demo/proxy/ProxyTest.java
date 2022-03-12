package com.example.demo.proxy;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

public class ProxyTest {
    @Test
    public void test() {
        HelloProxy helloProxy = new HelloProxy();
        System.out.println(helloProxy.getClass().getName());
        helloProxy.setDelegate(new PersonHello());
        IHello iHello = helloProxy.getProxyingHello();
        iHello.sayHello();
    }

    @Test
    public void testHelloInvocationHandler() {
        IHello hello = (IHello) Proxy.newProxyInstance(PersonHello.class.getClassLoader(),
                PersonHello.class.getInterfaces(),
                new HelloInvocationHandler(new PersonHello()));
        hello.sayHello();
        hello.sayBye();
    }
}