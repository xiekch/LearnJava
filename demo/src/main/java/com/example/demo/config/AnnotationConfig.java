package com.example.demo.config;

import com.example.demo.annotation.Person;
import com.example.demo.factory.ConnectionFactory;
import com.example.demo.model.Boss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(value = { "com.example.demo.controller", "com.example.demo.model",
        "com.example.demo.annotation" }, excludeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { Boss.class }) })
@Import(com.example.demo.aspect.Logging.class)
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

    @Bean
    public ConnectionFactory connectionFactory() {
        try {
            return new ConnectionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}