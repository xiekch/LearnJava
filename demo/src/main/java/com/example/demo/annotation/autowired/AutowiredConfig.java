package com.example.demo.annotation.autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.example.demo.annotation.autowired")
@Configuration
public class AutowiredConfig {
    @Bean("bookdao2")
    public BookDao getBookdao() {
        return new BookDao(2);
    }
}