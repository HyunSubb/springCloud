package com.nhnacademy.myblogapi.blog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "blogs")
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(unique = true)
    private String blogFid;

    private String blogName;

    private LocalDateTime createdAt;

    public Blog(String blogFid, String blogName) {
        this.blogFid = blogFid;
        this.blogName = blogName;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(String blogName) {
        this.blogName = blogName;
    }
}
