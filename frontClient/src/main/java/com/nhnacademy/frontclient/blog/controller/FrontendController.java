package com.nhnacademy.frontclient.blog.controller;

import com.nhnacademy.frontclient.blog.service.GatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend")
@RequiredArgsConstructor
public class FrontendController {
    private final GatewayService gatewayService;

    @GetMapping("/first-service")
    public ResponseEntity<String> getFirstServiceData() {
        String data = gatewayService.fetchFirstServiceData();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/second-service")
    public ResponseEntity<String> getSecondServiceData() {
        String data = gatewayService.fetchSecondServiceData();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/api/v1/blogs/{id}")
    public ResponseEntity<String> getBlogApi(@PathVariable Long id) {
        String data = gatewayService.myBlogServiceData(id);
        return ResponseEntity.ok(data);
    }
}