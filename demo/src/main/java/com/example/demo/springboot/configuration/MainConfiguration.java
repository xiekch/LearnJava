package com.example.demo.springboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:bean/beans.xml")
@Configuration
public class MainConfiguration {

}