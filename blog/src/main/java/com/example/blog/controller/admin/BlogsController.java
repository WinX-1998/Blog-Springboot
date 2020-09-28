package com.example.blog.controller.admin;

import com.example.blog.VO.BlogQuery;
import com.example.blog.entity.Blog;
import com.example.blog.entity.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.java2d.loops.Blit;

import javax.servlet.http.HttpSession;


@RequestMapping("/admin")
@Controller
public class BlogsController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;



    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=3,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model)
    {
        model.addAttribute("types",typeService.listType());
        Page<Blog> page = blogService.listBlog(pageable, blog);
        model.addAttribute("page", page);
        return "/admin/blogs";

    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size=3,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "/admin/blogs :: blogList";

    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "/admin/blogs_input";
    }

    @GetMapping("/blogs/{id}/editInput")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "/admin/blogs_input";
    }

    @GetMapping("/blogs/{id}/deleteBlog")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

    @PostMapping("/blogs/add")
    public String post(Blog blog, RedirectAttributes attributes,HttpSession session){
        User user = (User) session.getAttribute("user");
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b ;
        if(blog.getId()==null){
            blog.setUser(user);
             b = blogService.saveBlog(blog);
        }else{
             b = blogService.updateBlog(blog.getId(), blog);
        }
        if(b==null) {
        attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

}
