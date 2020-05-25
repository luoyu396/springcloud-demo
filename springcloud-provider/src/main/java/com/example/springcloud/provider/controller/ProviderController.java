package com.example.springcloud.provider.controller;

import com.example.springcloud.provider.dao.UserRepository;
import com.example.springcloud.model.User;
import com.example.springcloud.util.json.JsonUtil;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
public class ProviderController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getInfo")
    public String getInfo(HttpServletRequest request) throws InterruptedException {
        String res = "providerInfo";
        res += request.getLocalPort();
        System.out.println(System.currentTimeMillis());
        return res;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        User nowUser = userRepository.save(user);
        return nowUser;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userRepository.getOne(id);
        return user;
    }
}
