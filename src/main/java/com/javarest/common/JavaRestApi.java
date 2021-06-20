package com.javarest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
@Configuration
@ComponentScan("com.javarest")
public class JavaRestApi {


    Logger logger = LoggerFactory.getLogger(JavaRestApi.class);
    public static void main(String[] args) {
        SpringApplication.run(JavaRestApi.class, args);
    }

}
