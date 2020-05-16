package com.example.springcloud.consumer;

import com.example.springcloud.api.service.ProviderAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderAPI providerAPI;

    String providerName = "http://provider/pro";

    @GetMapping("/testGetProviderInfo")
    public String testGetProviderInfo() {
        return restTemplate.getForObject(providerName+"/getInfo", String.class);
    }

    @GetMapping("/testFeign")
    public String testFeign() {
        return providerAPI.getInfo();
    }
}
