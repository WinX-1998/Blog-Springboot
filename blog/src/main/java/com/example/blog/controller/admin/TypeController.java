package com.example.blog.controller.admin;

import com.example.blog.entity.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size=5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }


    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "/admin/types_input";
    }

    @GetMapping("/types/{id}/editInput")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "/admin/types_input";
    }

    @PostMapping("/addTypes")
    public String addTypes(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","namaError","不能添加重复的分类名称");
        }
        if(result.hasErrors()){
            return "admin/types_input";
        }
        Type t = typeService.saveType(type);
        if(t==null){
            attributes.addFlashAttribute("message","新增失败");
        }
        else{
            attributes.addFlashAttribute("message","新增成功");
        }
        /*需要重定向，不然直接返回types页面不会执行查询page的过程*/
        return "redirect:/admin/types";
    }


    @PostMapping("/updateTypes/{id}")
    public String updateTypes(@Valid Type type, BindingResult result,@PathVariable Long id,RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","namaError","不能添加重复的分类名称");
        }
        if(result.hasErrors()){
            return "admin/types_input";
        }
        Type t = typeService.updateType(id,type);
        if(t==null){
            attributes.addFlashAttribute("message","更新失败");
        }
        else{
            attributes.addFlashAttribute("message","更新成功");
        }
        /*需要重定向，不然直接返回types页面不会执行查询page的过程*/
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/deleteType")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
