package com.example.blog.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {

    @GetMapping("/aboutMe")
    public String about(){
        return "aboutMe";
    }
}
