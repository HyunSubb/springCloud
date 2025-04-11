package com.nhnacademy.myblogapi.blog.service.impl;

import com.nhnacademy.myblogapi.blog.domain.Blog;
import com.nhnacademy.myblogapi.blog.dto.BlogCreateRequest;
import com.nhnacademy.myblogapi.blog.dto.BlogResponse;
import com.nhnacademy.myblogapi.blog.dto.BlogUpdateRequest;
import com.nhnacademy.myblogapi.blog.exception.AlreadyExistBlogFidException;
import com.nhnacademy.myblogapi.blog.exception.BlogNotFoundException;
import com.nhnacademy.myblogapi.blog.repository.BlogRepository;
import com.nhnacademy.myblogapi.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    // 생성자 주입
    private final BlogRepository blogRepository;

    @Override
    public BlogResponse createBlog(BlogCreateRequest blogCreateRequest) {

        if(blogRepository.existsByBlogFid(blogCreateRequest.getBlogFid())) {
            throw new AlreadyExistBlogFidException(blogCreateRequest.getBlogFid());
        }

        Blog blog = new Blog(
            blogCreateRequest.getBlogFid(),
            blogCreateRequest.getBlogName()
        );

        Blog savedBlog = blogRepository.save(blog);

        return blogResponseMapper(savedBlog);
    }

    @Override
    public BlogResponse getBlog(Long blogId) {

        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException(blogId));

        return blogResponseMapper(blog);
    }

    @Override
    public BlogResponse updateBlog(Long blogId, BlogUpdateRequest blogUpdateRequest) {

        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new BlogNotFoundException(blogId));
        blog.update(blogUpdateRequest.getBlogNmae()); // 변경 감지로 인해서 영속성 컨텍스트에 존재하는 엔터티의 값만 변경해주면 알아서 레포지토리에 반영된다.

        return blogResponseMapper(blog);
    }

    @Override
    public void deleteBlog(Long blogId) {
        if(!blogRepository.existsById(blogId)) {
            throw new BlogNotFoundException(blogId);
        }
        blogRepository.deleteById(blogId);
    }

    private BlogResponse blogResponseMapper(Blog blog) {
        return new BlogResponse(
                blog.getBlogId(),
                blog.getBlogFid(),
                blog.getBlogName()
        );
    }
}
