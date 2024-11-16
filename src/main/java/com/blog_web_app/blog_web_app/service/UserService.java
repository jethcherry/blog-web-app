package com.blog_web_app.blog_web_app.service;

import com.blog_web_app.blog_web_app.dto.RegisterDto;

public interface UserService {
    void saveUser(RegisterDto registerDto);
}
