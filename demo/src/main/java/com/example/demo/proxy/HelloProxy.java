package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloProxy {
    private IHello delegate;

    /**
     * @param delegate the delegate to set
     */
    public void setDelegate(IHello delegate) {
        this.delegate = delegate;
    }

    public IHello getProxyingHello() {
        InvocationHandler invocationHandler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before hello");
                Object result = method.invoke(delegate, args);
                System.out.println("after hello");
                return result;
            }
        };

        return (IHello) Proxy.newProxyInstance(PersonHello.class.getClassLoader(), PersonHello.class.getInterfaces(),
                invocationHandler);
    }

}
