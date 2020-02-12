package com.example.demo.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String brand;
    private int price;
    private String corp;

    {
        this.brand = "";
        System.out.println("Car class");
    }

    static {
        // this.brand = "";
        System.out.println("static Car class code");
    }

    public Car() {
        System.out.println("Car's constructor");
    }

    public Car(String brand) {
        System.out.println("Car's constructor");
        this.brand = brand;
    }

    public Car(String brand, int price, String corp) {
        System.out.println("Car's constructor");
        this.brand = brand;
        this.price = price;
        this.corp = corp;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "brand:" + this.brand + " price:" + this.price + " corp:" + this.corp;
    }

    @PostConstruct
    public void init() {
        System.out.println("initing car ...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroying car ...");
    }
}