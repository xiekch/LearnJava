package com.example.demo.model;

import java.util.Map;

public class Boss {
    private String name;
    private Map<String, Car> cars;

    public Boss() {
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param cars the cars to set
     */
    public void setCars(Map<String, Car> cars) {
        this.cars = cars;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the cars
     */
    public Map<String, Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "name:" + this.name + " cars:" + this.cars;
    }
}