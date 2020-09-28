package com.example.blog.service;

import com.example.blog.VO.BlogQuery;
import com.example.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    public Blog getBlog(Long id);

    public Blog getAndConvert(Long id);

    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    public Page<Blog>listBlog(Pageable pageable);

    public Page<Blog>listBlog(Long tagId,Pageable pageable);

    public Page<Blog>listBlog(String query,Pageable pageable);

    public Map<String,List<Blog>>archivesBlog();

    public Long countBlog();

    public List<Blog>listRecommendTop(Integer size);

    public Blog saveBlog(Blog blog);

    public Blog updateBlog(Long id,Blog blog);

    public void deleteBlog(Long id);
}
