package com.example.demo.factory;

import com.example.demo.model.HelloWorld;
import com.example.demo.model.Car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class FactoryTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beansFactory.xml");

		Car car1 = (Car) ac.getBean("car");
		System.out.println(car1);

		Car car2 = (Car) ac.getBean("car2");
		System.out.println(car2);
		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);
		Car car3 = (Car) ac.getBean("car3");
		System.out.println(car3);

		ac.close();
		// SpringApplication.run(DemoApplication.class, args);

	}

}
