package com.example.demo.cycle;

import com.example.demo.model.Car;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CycleTest {

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean/beansCycle.xml");

		Car car1 = (Car) ac.getBean("car");
		System.out.println(car1);

		Car car2 = (Car) ac.getBean("car");
		System.out.println(car1 == car2);
		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);

		((ClassPathXmlApplicationContext) ac).close();
	}

}
