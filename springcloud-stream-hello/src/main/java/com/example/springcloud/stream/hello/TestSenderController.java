package com.example.springcloud.stream.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanbb
 * @create 2020-06-20 17:00
 */
@RestController
public class TestSenderController {

    @Autowired
    private SinkSender sinkSender;

    @Autowired
    @Qualifier("q1")
    private MessageChannel messageChannel;

    @GetMapping("/test")
    public String test() {
        boolean flag = sinkSender.output().send(MessageBuilder.withPayload("from sinkSender").build());
        return "发送消息："+flag;
    }

    @GetMapping("/test1")
    public String test1() {
        String userInfo = "{\"name\":\"tanbb\", \"age\":32}";
        boolean flag = messageChannel.send(MessageBuilder.withPayload(userInfo).build());
        return "发送消息："+flag;
    }
}
