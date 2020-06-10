package com.example.springcloudrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tanbb
 * @create 2020-06-10 17:15
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void receiver(String hello) {
        System.out.println("Receiver:" + hello);
    }
}
