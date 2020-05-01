package com.lolisite.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cap_ljf
 * @description 服务注册与发现中心
 * @create 2020/04/04 16:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }

}
