package com.example.blog.service;


import com.example.blog.dao.UserRepository;
import com.example.blog.entity.User;
import org.springframework.stereotype.Service;


public interface UserService{
 public User findByUsernameAndPassword(String username,String password);
}
