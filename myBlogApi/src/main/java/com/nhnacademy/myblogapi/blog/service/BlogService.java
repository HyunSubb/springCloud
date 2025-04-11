package com.nhnacademy.myblogapi.blog.service;

import com.nhnacademy.myblogapi.blog.dto.BlogCreateRequest;
import com.nhnacademy.myblogapi.blog.dto.BlogResponse;
import com.nhnacademy.myblogapi.blog.dto.BlogUpdateRequest;

public interface BlogService {

    BlogResponse createBlog(BlogCreateRequest blogCreateRequest);

    BlogResponse getBlog(Long blogId);

    BlogResponse updateBlog(Long blogId, BlogUpdateRequest blogUpdateRequest);

    void deleteBlog(Long blogId);
}
