package com.example.springcloud.stream.app2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

@EnableBinding(value = {Processor.class})
public class App2 {

    private static Logger logger = LoggerFactory.getLogger(App2.class);

    @StreamListener(Processor.INPUT)
    public void receiver(Object info) {
        logger.info("receiver:" + info);
    }


    /*@Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> timerMessageSource() {
        return () -> new GenericMessage<>("{\"name\":\"tanbb\", \"age\":32}");
    }*/
}
