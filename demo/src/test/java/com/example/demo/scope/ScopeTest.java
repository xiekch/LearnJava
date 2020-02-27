package com.example.demo.scope;

import com.example.demo.model.HelloWorld;
import com.example.demo.model.Car;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {

	@Test
	public void test() {
		System.out.println("before");
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean/beansScope.xml");
		System.out.println("after");
		HelloWorld helloWorld = (HelloWorld) ac.getBean("helloWorld");
		helloWorld.sayHello();

		Car car1 = (Car) ac.getBean("car");
		Car car2 = (Car) ac.getBean("car");
		System.out.println(car1 == car2);

		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);

		((ClassPathXmlApplicationContext) ac).close();
	}

}
