package com.example.springcloud.provider.controller;

import com.example.springcloud.provider.dao.UserRepository;
import com.example.springcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        System.out.println(System.currentTimeMillis());
        return res;
    }

    @PostMapping("/user")
    public String saveUser(User user) {
        User nowUser = userRepository.save(user);
        String res = nowUser.toString();
        return res;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userRepository.getOne(id);
        return user;
    }
}
