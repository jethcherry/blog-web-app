package com.blog_web_app.blog_web_app.controller;


import com.blog_web_app.blog_web_app.dto.RegisterDto;
import com.blog_web_app.blog_web_app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegister(Model model) {
        //this object holds form data
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";

    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") RegisterDto user) {
        userService.saveUser(user);
        return "redirect:/register?success";

    }


}
