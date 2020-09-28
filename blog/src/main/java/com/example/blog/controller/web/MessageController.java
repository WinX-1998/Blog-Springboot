package com.example.blog.controller.web;


import com.example.blog.entity.Message;
import com.example.blog.entity.User;
import com.example.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


    @Value("${message.avatar}")
    private String avatar;


    @GetMapping("/message")
    public String message(@PageableDefault(size=5,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable,Model model){
        model.addAttribute("page",messageService.ListMessage(pageable));
        return "/messages";
    }

    @GetMapping("/messages")
    public String messages(@ModelAttribute("page")String page,Model model){
        Pageable pageable=null;
       if(page!=""&&page!=null){
           int pageNum = Integer.parseInt(page);
           Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
            pageable=PageRequest.of(pageNum,5,sort);
       }else{
           Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
            pageable=PageRequest.of(1,5,sort);
       }
        model.addAttribute("page",messageService.ListMessage(pageable));
        return "messages :: messageList";
    }


    @PostMapping("/postMessage")
    public String post(@RequestBody String page, Message message, HttpSession session, RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        }else{
            message.setAvatar(avatar);
        }
        String[] split = page.split("&");
        String pageNum=null;
        if(split.length>4)
        {
            String pageNums=split[4];
            String[] split1 = pageNums.split("=");
            pageNum=split1[1];
        }else{
            pageNum="0";
        }
        redirectAttributes.addAttribute("page",pageNum);
        messageService.saveMessage(message);
        return "redirect:/messages";
    }
}
