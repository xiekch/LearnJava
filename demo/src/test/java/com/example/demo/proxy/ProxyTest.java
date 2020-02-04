package com.example.demo.proxy;

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
}