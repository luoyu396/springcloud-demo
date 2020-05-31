package com.example.springcloud.api.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanbb
 * @create 2020-05-30 10:48
 */
@RestController
@RequestMapping("/local")
public class LocalController {

    @GetMapping("/info")
    public String getInfo() {
        return "local info";
    }
}
