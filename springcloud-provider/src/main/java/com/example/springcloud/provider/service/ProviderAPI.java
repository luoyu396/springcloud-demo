package com.example.springcloud.provider.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="provider", path = "/pro")
public interface ProviderAPI {

    @GetMapping("/getInfo")
    public String getInfo();
}
