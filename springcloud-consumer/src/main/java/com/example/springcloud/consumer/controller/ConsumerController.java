package com.example.springcloud.consumer.controller;

import com.example.springcloud.consumer.service.UserService;
import com.example.springcloud.model.User;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private UserService userService;

    @GetMapping("/testByRibbon")
    public String testGetProviderInfo() {
        return userService.getInfoRibbon();
    }

    @GetMapping("/testByFeign")
    public String testFeign() {
       return userService.getInfoFeign();
    }

    @PostMapping("/testSaveUserRibbon")
    public User testSaveUser(User user) {
        return userService.saveUserRibbon(user);
    }

    @PostMapping("/testSaveUserFeign")
    public User testSaveUserFeign(User user) {
        return userService.saveUserFeign(user);
    }

    @GetMapping("/testGetUserRibbon/{id}")
    public User testGetUser(@PathVariable String id) {
        User user = userService.getUserRibbon(id);
        return user;
    }

    @GetMapping("/testGetUserFeign/{id}")
    public User testGetUserFeign(@PathVariable String id) {
        User user = userService.getUserFeign(id);
        return user;
    }


}
