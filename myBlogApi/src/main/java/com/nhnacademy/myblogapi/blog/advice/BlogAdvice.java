package com.nhnacademy.myblogapi.blog.advice;

import com.nhnacademy.myblogapi.blog.controller.BlogController;
import com.nhnacademy.myblogapi.blog.exception.AlreadyExistBlogFidException;
import com.nhnacademy.myblogapi.blog.exception.BlogNotFoundException;
import com.nhnacademy.myblogapi.common.error.CommonException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = BlogController.class)
public class BlogAdvice {

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<CommonException> blogNotFoundExceptionHandler(
            BlogNotFoundException blogNotFoundException,
            HttpServletRequest request
    ) {
        CommonException commonException = new CommonException(
                blogNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonException);
    }

    public ResponseEntity<CommonException> AlreadyExistExceptionHandler(
            AlreadyExistBlogFidException alreadyExistBlogFidException,
            HttpServletRequest request
    ) {
        CommonException commonException = new CommonException(
                alreadyExistBlogFidException.getMessage(),
                HttpStatus.CONFLICT.value(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(commonException);
    }
}
