package com.example.springcloud.api.service;


import com.example.springcloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="provider", path = "/pro")
public interface ProviderAPI {

    @GetMapping("/getInfo")
    String getInfo();

    @PostMapping("/user")
    User saveUser(@RequestBody User user);

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") String id);

    @GetMapping("/users/{ids}")
    List<User> getUsersByIds(@PathVariable("ids") String ids);
}
