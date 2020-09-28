package com.example.blog.controller.admin;
import com.example.blog.dao.UserRepository;
import com.example.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static com.example.blog.utils.MD5Utils.code;


@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public String logPage(){
        return "/admin/login";
    }


//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                              @RequestParam String password,
//                              HttpSession session,
//                              RedirectAttributes redirectAttributes){
//        User user = userRepository.findByUsernameAndPassword(username, password);
//
//        if(user!=null){
//            user.setPassword(null);
//            session.setAttribute("user",user);
//            return "/admin/index";
//
//        }else{
//            redirectAttributes.addAttribute("errorMessage","用户名或密码错误");
//          return "redirect:/admin";
//        }
//    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){
        User user = userRepository.findByUsernameAndPassword(username, code(password));

        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "/admin/index";

        }else{
           redirectAttributes.addFlashAttribute("errorMessage","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
