package com.dynamicproxyadapter;

import com.dynamicproxyadapter.annotation.EnableThirdPartyProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableThirdPartyProxy
public class DynamicProxyAdapterSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DynamicProxyAdapterSpringBootApplication.class);
        springApplication.run(args);
    }
}