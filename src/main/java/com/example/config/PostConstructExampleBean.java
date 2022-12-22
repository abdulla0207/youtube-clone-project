package com.example.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PostConstructExampleBean {
    @PostConstruct
    public void init() {
        System.out.println("init");
    }
}