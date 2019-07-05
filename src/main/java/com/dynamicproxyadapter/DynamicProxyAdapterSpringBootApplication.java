package com.dynamicproxyadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicProxyAdapterSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DynamicProxyAdapterSpringBootApplication.class);
        springApplication.run(args);
    }
}