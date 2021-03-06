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
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(value = { "com.example.demo.controller", "com.example.demo.model", "com.example.demo.annotation",
        "com.example.demo.config" }, excludeFilters = {
                @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { Boss.class }) })
@Import(com.example.demo.aspect.Logging.class)
@PropertySource("classpath:/annotation/person.properties")
public class AnnotationConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Person person() {
        return new Person("Gril", 30);
    }

    @Scope("prototype")
    @Bean(value = "person2", destroyMethod = "destroy")
    public Person person1() {
        Person person = new Person("Simon", 26);
        person.setName("Simon");
        person.setAge(26);
        return person;
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