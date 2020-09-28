package com.example.blog.dao;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    public List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
