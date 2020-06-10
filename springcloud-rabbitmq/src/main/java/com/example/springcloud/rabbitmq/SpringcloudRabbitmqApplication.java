package com.example.springcloud.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringcloudRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudRabbitmqApplication.class, args);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
