package com.example.demo.factory;

import com.example.demo.model.Car;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean/beansFactory.xml");

		Car car1 = (Car) ac.getBean("car");
		System.out.println("car1: " + car1);

		Car car2 = (Car) ac.getBean("car2");
		System.out.println("car2: " + car2);
		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);
		Car car3 = (Car) ac.getBean("car3");
		System.out.println("car3: " + car3);

		ac.close();
	}
}
