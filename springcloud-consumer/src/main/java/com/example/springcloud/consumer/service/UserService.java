package com.example.springcloud.consumer.service;

import com.example.springcloud.api.service.ProviderAPI;
import com.example.springcloud.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanbb
 * @create 2020-05-26 14:50
 */
@Service
public class UserService {

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
    public String getInfoRibbon() {
        return restTemplate.getForObject(providerName+"/getInfo", String.class);
    }

    //设置出现超时(默认1000ms)断路时，跳转的服务降级方法
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            //设置超时时间2000ms
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String getInfoFeign() {
        return providerAPI.getInfo();
    }

    //保存user
    public User saveUserRibbon(User user) {
        return restTemplate.postForObject(providerName+"/user", user, User.class);
    }

    //保存user
    public User saveUserFeign(User user) {
        return providerAPI.saveUser(user);
    }

    @HystrixCollapser(batchMethod = "getUsersRibbon", scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties = {
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    //根据id查询user
    public User getUserRibbon(String id) {
        User user = restTemplate.getForObject(providerName+"/user/{1}", User.class, id);
        return user;
    }

    //根据id查询user
    public User getUserFeign(String id) {
        User user = providerAPI.getUserById(id);
        return user;
    }

    @HystrixCommand
    //根据id集合查询user集合
    public List<User> getUsersRibbon(List<String> ids) {
        System.out.println(ids);
        User[] users = restTemplate.getForObject(providerName+"/users/{ids}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(users);
    }

    //根据id集合查询user集合
    public List<User> getUsersFeign(List<String> ids) {
        return providerAPI.getUsersByIds(StringUtils.join(ids, ","));
    }

    //降级方法
    private String error() {
        return "超时，服务异常";
    }

    //降级方法
    public User queryUserBackMethod(String id, Throwable e) {
        e.printStackTrace();
        return new User();
    }
}
