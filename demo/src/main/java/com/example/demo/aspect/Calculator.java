package com.example.demo.aspect;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Calculator {
    public int add(int i, int j) {
        return i + j;
    }

    public int mul(int i, int j) {
        return i * j;
    }

    public int div(int i, int j) {
        return i / j;
    }

    public int subtract(int i, int j) {
        return i - j;
    }
}