package com.nhnacademy.myblogapi.common.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // getter setter
@RequiredArgsConstructor // 모든 필드 생성자 생성
public class CommonException {
    private final String message;
    private final int statusCode;
    private final String uri;
}
