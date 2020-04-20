package com.woo.eurekaclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @Value("${name}")
    private String name;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("线程池名称："+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world"+"端口："+port;
    }

    String fallback(){
        return "服务器繁忙";
    }

    @RequestMapping("/otherService")
    public String otherService(){
        return "我是其他服务";
    }

    @RequestMapping("/test-cloud-config")
    public String testCloudConfig(){
        return name;
    }
}
