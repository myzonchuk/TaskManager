package com.example.nerdy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class NerdyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NerdyApplication.class, args);
    }

}
