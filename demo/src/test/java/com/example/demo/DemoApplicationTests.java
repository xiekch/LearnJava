package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void name() {
		name(1);
	}

	public static <T> void name(T t) {
		System.out.println(t);
		System.out.println(t.getClass());
	}
}
