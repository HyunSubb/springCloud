package com.nhnacademy.frontclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // FeignClient 활성화
@EnableDiscoveryClient // Eureka 클라이언트 활성화
public class FrontClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontClientApplication.class, args);
    }

}
