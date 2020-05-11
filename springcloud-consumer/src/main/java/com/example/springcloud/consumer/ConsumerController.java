package com.example.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    String providerName = "http://provider/pro";

    @GetMapping("/testGetProviderInfo")
    public String testGetProviderInfo() {
        return restTemplate.getForObject(providerName+"/getInfo", String.class);
    }
}
