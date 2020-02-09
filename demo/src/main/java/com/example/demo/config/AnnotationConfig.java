package com.example.demo.config;

import com.example.demo.annotation.Person;
import com.example.demo.model.Boss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(value = { "com.example.demo.controller", "com.example.demo.model",
        "com.example.demo.annotation" }, excludeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { Boss.class }) })
public class AnnotationConfig {

    @Bean
    public Person person() {
        return new Person("Gril", 30);
    }

    @Scope("prototype")
    @Bean("person2")
    public Person person1() {
        return new Person("Simon", 26);
    }

    @Lazy
    @Bean
    public Person person3() {
        return new Person("Dan", 50);
    }
}