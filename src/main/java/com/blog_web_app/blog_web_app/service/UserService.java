package com.blog_web_app.blog_web_app.service;

import com.blog_web_app.blog_web_app.dto.RegisterDto;
import com.blog_web_app.blog_web_app.entity.UserEntity;

public interface UserService {
    void saveUser(RegisterDto registerDto);

    UserEntity findByEmail( String email);
}
