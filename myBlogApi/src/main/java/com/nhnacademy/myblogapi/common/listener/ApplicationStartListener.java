package com.nhnacademy.myblogapi.common.listener;

import com.nhnacademy.myblogapi.blog.dto.BlogCreateRequest;
import com.nhnacademy.myblogapi.blog.dto.BlogResponse;
import com.nhnacademy.myblogapi.blog.service.BlogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
@ConditionalOnProperty(havingValue = "true", prefix = "blog", name = "test")
public class ApplicationStartListener implements ApplicationListener<ApplicationReadyEvent> {
    private final BlogService blogService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        BlogCreateRequest blogCreateRequest = new BlogCreateRequest(
                "nhnacademy", "blog"
        );

        BlogResponse blogResponse = blogService.createBlog(blogCreateRequest);
    }
}
