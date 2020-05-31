package com.example.springcloud.api.gateway;

import com.example.springcloud.api.gateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class SpringcloudApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudApiGatewayApplication.class, args);
    }

    /*@Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }*/

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }
}
