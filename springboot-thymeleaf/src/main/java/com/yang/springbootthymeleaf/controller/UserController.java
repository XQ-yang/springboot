package com.yang.springbootthymeleaf.controller;

import com.yang.springbootthymeleaf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: 小强
 * @date: 2022/10/26 0026
 * @IDE: IntelliJ IDEA
 */
@Controller
public class UserController {
    @GetMapping("/index")
    public String hello(Model model){
        User user = new User(1, "wangwu", "emp");
        model.addAttribute("user", user);
        return "views/index";
    }
}
