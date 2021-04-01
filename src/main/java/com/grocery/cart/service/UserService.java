package com.grocery.cart.service;

import com.grocery.cart.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    private static final String endpoint = "http://USERMICROSERVICE/user/users";

    @HystrixCommand(fallbackMethod = "",
    threadPoolKey = "UserService_ThreadPool",
    threadPoolProperties = {
            @HystrixProperty(name="coreSize",value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    },
    commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "15000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "20000"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "5")
    }
    )
    public List<User> getUserList(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange(endpoint, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<User>>() {
        }).getBody();
    }
}
