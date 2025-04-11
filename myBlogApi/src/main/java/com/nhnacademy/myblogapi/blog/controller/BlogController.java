package com.nhnacademy.myblogapi.blog.controller;

import com.nhnacademy.myblogapi.blog.dto.BlogCreateRequest;
import com.nhnacademy.myblogapi.blog.dto.BlogResponse;
import com.nhnacademy.myblogapi.blog.dto.BlogUpdateRequest;
import com.nhnacademy.myblogapi.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/blogs"})
public class BlogController {
    // 생성자 주입
    private final BlogService blogService;

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlog(@PathVariable("id") Long blogId) {
        BlogResponse blogResponse = blogService.getBlog(blogId);

        return ResponseEntity
                .ok(blogResponse);
    }

    @PostMapping
    public ResponseEntity<BlogResponse> postBlog(@Validated @RequestBody BlogCreateRequest blogCreateRequest) {
         BlogResponse blogResponse = blogService.createBlog(blogCreateRequest);

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(blogResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> putBlog(@PathVariable("id") Long blogId, @Validated @RequestBody BlogUpdateRequest blogUpdateRequest) {
        BlogResponse blogResponse = blogService.updateBlog(blogId, blogUpdateRequest);

        return ResponseEntity
                .ok(blogResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BlogResponse> deleteBlog(@PathVariable("id") Long blogId) {
        blogService.deleteBlog(blogId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }



}
