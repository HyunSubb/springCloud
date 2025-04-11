package com.nhnacademy.userservice.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
//@AllArgsConstructor
//@NoArgsConstructor
public class Greeting {
    @Value("${greeting.message}") // 이것도 Environment로 가져온 것 처럼 yml에서 설정한 값을 가져올 수 있음.
    private String message;
}
