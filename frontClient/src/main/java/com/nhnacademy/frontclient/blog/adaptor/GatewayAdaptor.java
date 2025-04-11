package com.nhnacademy.frontclient.blog.adaptor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "apigateway-service", url = "http://localhost:8000")
public interface GatewayAdaptor {
    @GetMapping("/first-service/welcome")
    String getFirstServiceData();

    @GetMapping("/second-service/welcome")
    String getSecondServiceData();

    @GetMapping("/api/v1/blogs/{id}")
    String getMyBlogData(@PathVariable Long id);
}