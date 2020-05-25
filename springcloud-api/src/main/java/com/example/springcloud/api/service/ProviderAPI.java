package com.example.springcloud.api.service;


import com.example.springcloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="provider", path = "/pro")
public interface ProviderAPI {

    @GetMapping("/getInfo")
    String getInfo();

    @PostMapping("/user")
    User saveUser(@RequestBody User user);

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") String id);
}
