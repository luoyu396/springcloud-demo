package com.example.springcloud.consumer;

import com.example.springcloud.api.service.ProviderAPI;
import com.example.springcloud.util.json.JsonUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.example.springcloud.model.User;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProviderAPI providerAPI;

    String providerName = "http://provider/pro";


    //设置出现超时(默认1000ms)断路时，跳转的服务降级方法
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            //设置超时时间2000ms
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/testByRabbion")
    public String testGetProviderInfo() {
        return restTemplate.getForObject(providerName+"/getInfo", String.class);
    }

    @GetMapping("/testByFeign")
    public String testFeign() {
        return providerAPI.getInfo();
    }

    @CacheResult
    @HystrixCommand
    @GetMapping("/testCache")
    public String testCache(Long id) {
        System.out.println(id);
        return restTemplate.getForObject(providerName+"/getInfo", String.class);
    }

    private String error() {
        return "超时，服务异常";
    }


    @PostMapping("/testSaveUserRabbion")
    public User testSaveUser(User user) {
        return restTemplate.postForObject(providerName+"/user", user, User.class);
    }

    @CacheResult(cacheKeyMethod = "getUserKey")
    @HystrixCommand(fallbackMethod = "queryUserBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "requestCache.enabled", value = "true")
    })
    @GetMapping("/testGetUserRabbion/{id}")
    public User testGetUser(@PathVariable String id) {
        User user = restTemplate.getForObject(providerName+"/user/{1}", User.class, id);
        return user;
    }

    @PostMapping("/testSaveUserFeign")
    public User testSaveUserFeign(User user) {
        return providerAPI.saveUser(user);
    }

    @CacheResult(cacheKeyMethod = "getUserKey")
    @HystrixCommand(fallbackMethod = "queryUserBackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "requestCache.enabled", value = "true")
    })
    @GetMapping("/testGetUserFeign/{id}")
    public User testGetUserFeign(@PathVariable String id) {
        User user = providerAPI.getUserById(id);
        return user;
    }

    public String getUserKey(String id) {
        return id;
    }

    public User queryUserBackMethod(String id, Throwable e) {
        e.printStackTrace();
        return new User();
    }
}
