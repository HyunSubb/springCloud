package com.nhnacademy.myblogapi.blog.exception;

public class AlreadyExistBlogFidException extends RuntimeException {
    public AlreadyExistBlogFidException(String blogFid) {
        super("Blog FID: " + blogFid);
    }
}
