package com.example.demo.factory;

import java.util.Map;

import com.example.demo.model.Car;

import java.util.HashMap;

public class StaticCarFactory {
    static Map<String, Car> cars = new HashMap<>();
    static {
        cars.put("Banz", new Car("Banz"));
        cars.put("Ford", new Car("Ford"));
    }

    public static Car getCar(String name) {
        return cars.get(name);
    }
}