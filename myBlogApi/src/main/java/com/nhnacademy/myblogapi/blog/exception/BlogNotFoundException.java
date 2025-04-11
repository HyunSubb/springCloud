package com.nhnacademy.myblogapi.blog.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(Long blogId) {
        super("Could not find blog with id " + blogId);
    }
}
