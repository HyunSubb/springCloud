package com.nhnacademy.myblogapi.blog.repository;

import com.nhnacademy.myblogapi.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    boolean existsByBlogFid(String blogFid);
}
