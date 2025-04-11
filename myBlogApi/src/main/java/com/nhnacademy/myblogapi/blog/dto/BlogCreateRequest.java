package com.nhnacademy.myblogapi.blog.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BlogCreateRequest {

    @NotEmpty
    private final String blogFid;

    @NotEmpty
    private final String blogName;
}
