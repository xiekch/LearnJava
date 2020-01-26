package com.example.demo.factory;

import org.springframework.beans.factory.FactoryBean;
import com.example.demo.model.Car;

public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Car getObject() throws Exception {
        // TODO Auto-generated method stub
        return new Car(this.brand);
    }

    @Override
    public Class<Car> getObjectType() {
        // TODO Auto-generated method stub
        return Car.class;
    }

}