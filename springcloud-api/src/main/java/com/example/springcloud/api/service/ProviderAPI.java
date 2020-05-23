package com.example.springcloud.api.service;


import com.example.springcloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="provider", path = "/pro")
public interface ProviderAPI {

    @GetMapping("/getInfo")
    String getInfo();

    @PostMapping("/user")
    String saveUser(@RequestBody User user);

    @GetMapping("/user/{id}")
    String getUserById(@PathVariable("id") String id);
}
