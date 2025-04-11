package com.nhnacademy.myblogapi.blog.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogResponse {

    private final Long blogId;

    private final String blogFid;

    private final String blogName;
}
