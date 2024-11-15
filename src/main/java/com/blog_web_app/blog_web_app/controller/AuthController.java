package com.blog_web_app.blog_web_app.controller;


import com.blog_web_app.blog_web_app.dto.RegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegister(Model model) {
        //this object holds form data
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";

    }

}
