package com.example.springcloud.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProviderController {

    @GetMapping("/getInfo")
    public String getInfo(HttpServletRequest request) {
        String res = "providerInfo";
        res += request.getLocalPort();
        System.out.println(System.currentTimeMillis());
        return res;
    }
}
