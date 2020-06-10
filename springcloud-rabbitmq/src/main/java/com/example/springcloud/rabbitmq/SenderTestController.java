package com.example.springcloud.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanbb
 * @create 2020-06-10 17:29
 */
@RestController
public class SenderTestController {

    @Autowired
    private Sender sender;

    @GetMapping("/testSender")
    public String testSender() {
        sender.send();
        return "send";
    }
}
