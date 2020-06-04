package com.example.springcloud.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;

    @GetMapping("/from")
    public String getFrom() {
        return this.from;
    }

    @GetMapping("/from1")
    public String getFrom1() {
        return environment.getProperty("from", "null");
    }
}
