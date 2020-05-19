package com.example.springcloud.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
public class ProviderController {

    @GetMapping("/getInfo")
    public String getInfo(HttpServletRequest request) throws InterruptedException {
        String res = "providerInfo";
        res += request.getLocalPort();
        int sleepTime = new Random().nextInt(3000);
        Thread.sleep(sleepTime);
        System.out.println(System.currentTimeMillis());
        return res;
    }
}
