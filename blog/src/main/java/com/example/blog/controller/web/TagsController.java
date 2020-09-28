package com.example.blog.controller.web;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Tag;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagsController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PageableDefault(size=5,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id,  Model model){
        List<Tag> tags = tagService.listTagTop(1000);
        if(id==-1){
           id= tags.get(0).getId();
        }
        Page<Blog> blogs = blogService.listBlog(id,pageable);
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogs);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
