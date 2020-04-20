package com.woo.feignclient.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-client")
public interface ServiceAFeignClient {

    @RequestMapping("/hello")
    public String hello();
}
