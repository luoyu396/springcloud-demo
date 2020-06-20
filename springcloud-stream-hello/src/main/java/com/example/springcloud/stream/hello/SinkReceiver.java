package com.example.springcloud.stream.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value = {Sink.class, SinkSender.class})
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receiver(Object info) {
        logger.info("receiver:" + info);
    }

    @StreamListener("q1")
    public void receiver1(Object info) {
        logger.info("receiver1:" + info);
    }

}
