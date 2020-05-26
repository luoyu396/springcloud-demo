package com.example.springcloud.provider.controller;

import com.example.springcloud.provider.dao.UserRepository;
import com.example.springcloud.model.User;
import com.example.springcloud.util.json.JsonUtil;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class ProviderController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getInfo")
    public String getInfo(HttpServletRequest request) {
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

    @GetMapping("/users/{ids}")
    public List<User> getUsersByIds(@PathVariable String ids) {
        if(StringUtils.isBlank(ids)) {
            return Collections.emptyList();
        }
        List<String> idList = Arrays.asList(ids.split(","));
        List<User> useList = userRepository.getUsersByIds(idList);
        return useList;
    }
}
