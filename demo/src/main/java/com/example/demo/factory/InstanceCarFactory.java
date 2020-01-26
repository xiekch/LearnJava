package com.example.demo.factory;

import java.util.Map;

import com.example.demo.model.Car;

import java.util.HashMap;

public class InstanceCarFactory {
    private Map<String, Car> cars = new HashMap<>();
    {
        cars.put("Banz", new Car("Banz"));
        cars.put("Ford", new Car("Ford"));
    }

    public Car getCar(String name) {
        return cars.get(name);
    }
}