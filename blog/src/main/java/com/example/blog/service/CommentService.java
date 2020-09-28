package com.example.blog.service;

import com.example.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment>ListCommentByBlogId(Long blogId);

    public Comment saveComment(Comment comment);
}
