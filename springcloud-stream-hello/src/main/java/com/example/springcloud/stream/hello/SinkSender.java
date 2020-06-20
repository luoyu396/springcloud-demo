package com.example.springcloud.stream.hello;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * @author tanbb
 * @create 2020-06-20 16:58
 */

public interface SinkSender {

    @Output(Sink.INPUT)
    MessageChannel output();

    @Output("q1")
    MessageChannel output1();
}
