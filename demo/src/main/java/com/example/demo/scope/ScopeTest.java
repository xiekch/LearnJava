package com.example.demo.scope;

import com.example.demo.model.HelloWorld;
import com.example.demo.model.Car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class ScopeTest {

	public static void main(String[] args) {
		System.out.println("before");
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beansScope.xml");
		System.out.println("after");
		HelloWorld helloWorld=(HelloWorld)ac.getBean("helloWorld");
		helloWorld.sayHello();
		
		Car car1 = (Car)ac.getBean("car");
		Car car2 = (Car)ac.getBean("car");
		System.out.println(car1==car2);

		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);

		ac.close();
		// SpringApplication.run(DemoApplication.class, args);

	}

}
