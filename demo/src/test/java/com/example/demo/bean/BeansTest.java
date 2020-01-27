package com.example.demo.bean;

import com.example.demo.model.HelloWorld;
import com.example.demo.model.Boss;
import com.example.demo.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeansTest {

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		HelloWorld helloWorld = (HelloWorld) ac.getBean("helloWorld");
		helloWorld.sayHello();

		// Car car = ac.getBean(Car.class);
		// System.out.println(car);

		// Person person = (Person) ac.getBean("person");
		// System.out.println(person);

		Person person2 = (Person) ac.getBean("person2");
		System.out.println(person2);

		Boss boss = (Boss) ac.getBean("boss");
		System.out.println(boss);
		// System.out.println(person.getCar()==boss.getCars().get("a"));

		Person person3=(Person)ac.getBean("person4");
		System.out.println(person3);
		
		// SpringApplication.run(DemoApplication.class, args);

	}

}
