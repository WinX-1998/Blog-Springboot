package com.example.blog.service;


import com.example.blog.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {

    public Page<Message> ListMessage(Pageable pageable);

    public Message saveMessage(Message message);
}
