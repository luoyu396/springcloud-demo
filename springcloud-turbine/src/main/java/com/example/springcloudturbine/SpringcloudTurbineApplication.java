package com.example.springcloudturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudTurbineApplication.class, args);
    }

}
