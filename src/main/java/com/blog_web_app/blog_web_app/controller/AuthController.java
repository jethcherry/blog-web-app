package com.blog_web_app.blog_web_app.controller;


import com.blog_web_app.blog_web_app.dto.RegisterDto;
import com.blog_web_app.blog_web_app.entity.UserEntity;
import com.blog_web_app.blog_web_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //handle method to handle login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //handler method to handle user registration request
    @GetMapping("/register")
    public String showRegister(Model model) {
        //this object holds form data
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";

    }


    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegisterDto user, BindingResult result, Model model) {
        // Check if user with same email already exists
        UserEntity existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already a user with the same email id");
        }

        // If there are validation errors, return to the registration page with error messages
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register"; // Make sure to return to the registration page in case of errors
        }

        // Save user and redirect to success message
        userService.saveUser(user);
        return "redirect:/register?success";
    }


}
