package com.example.demo.model;

public class Person {
    private String name;
    private Car car;

    public Person() {
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name:" + this.name + " car:" + this.car;
    }
}