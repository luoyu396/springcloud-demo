package com.example.springcloud.consumer.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@ServletComponentScan(basePackages = "com.example.springcloud.consumer.filter")
@Configuration
public class FilterCompentConfig {
}
