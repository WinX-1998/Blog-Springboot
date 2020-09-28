package com.example.blog.service.Impl;


import com.example.blog.dao.MessageRepository;
import com.example.blog.entity.Message;
import com.example.blog.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Page<Message> ListMessage(Pageable pageable) {
       // Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        Page<Message> messages = messageRepository.findAllByParentMessageNull(pageable);
        Page<Message> messageList = eachMessage(messages);
        return messageList;
    }

    @Transactional
    @Override
    public Message saveMessage(Message message) {
        Long parentMessageId = message.getParentMessage().getId();
        if(parentMessageId!=-1){
            message.setParentMessage(messageRepository.getOne(parentMessageId));
        }else{
            message.setParentMessage(null);
        }
        message.setCreateTime(new Date());
        return messageRepository.save(message);
    }


   // * 循环每个顶级的评论节点
    private Page<Message>eachMessage(Page<Message>messages){
        List<Message> messagesView=new ArrayList<>();
        for(Message message:messages.getContent()){
            Message m = new Message();
            BeanUtils.copyProperties(message,m);
            messagesView.add(m);
        }
       // 合并评论的各层子代到第一级子代集合中
        combineChildren(messagesView);
        Page<Message>messagesPage=new Page<Message>() {
           @Override
           public int getTotalPages() {
              return  messages.getTotalPages();
           }

           @Override
           public long getTotalElements() {
               return messages.getTotalElements();
           }

           @Override
           public <U> Page<U> map(Function<? super Message, ? extends U> function) {
               return null;
           }

           @Override
           public int getNumber() {
               return messages.getNumber();
           }

           @Override
           public int getSize() {
               return messages.getSize();
           }

           @Override
           public int getNumberOfElements() {
               return messages.getNumberOfElements();
           }

           @Override
           public List<Message> getContent() {
               return messagesView;
           }

           @Override
           public boolean hasContent() {
               return messages.hasContent();
           }

           @Override
           public Sort getSort() {
               return messages.getSort();
           }

           @Override
           public boolean isFirst() {
               return messages.isFirst();
           }

           @Override
           public boolean isLast() {
               return messages.isLast();
           }

           @Override
           public boolean hasNext() {
               return messages.hasNext();
           }

           @Override
           public boolean hasPrevious() {
               return messages.hasPrevious();
           }

           @Override
           public Pageable nextPageable() {
               return messages.nextPageable();
           }

           @Override
           public Pageable previousPageable() {
               return messages.previousPageable();
           }

           @Override
           public Iterator<Message> iterator() {
               return messages.iterator();
           }
       };

        return messagesPage;
    }

    private void combineChildren(List<Message>messages){
        for(Message message:messages){
            List<Message>replys1=message.getReplyMessages();
            for(Message reply1:replys1){
           // 循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
           // 修改顶级节点的reply集合为迭代处理后的集合
            message.setReplyMessages(tempReplys);
           // 清楚临时存放区
            tempReplys=new ArrayList<>();
        }
    }
    //存放迭代找出的所有子代集合
    private List<Message>tempReplys=new ArrayList<>();

   /* 递归迭代
    *把每个顶级评各自的所有子评论存入临时存放区temReplys中*/

    private void recursively(Message message){
        //顶节点添加到临时存放集合
        tempReplys.add(message);
        if(message.getReplyMessages().size()>0){
            List<Message>replys=message.getReplyMessages();
            for(Message reply:replys){
                tempReplys.add(reply);
                if(reply.getReplyMessages().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
