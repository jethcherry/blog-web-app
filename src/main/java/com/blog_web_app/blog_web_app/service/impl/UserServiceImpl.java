package com.blog_web_app.blog_web_app.service.impl;

import com.blog_web_app.blog_web_app.dto.RegisterDto;
import com.blog_web_app.blog_web_app.entity.RoleEntity;
import com.blog_web_app.blog_web_app.entity.UserEntity;
import com.blog_web_app.blog_web_app.repository.RoleRepository;
import com.blog_web_app.blog_web_app.repository.UserRepository;
import com.blog_web_app.blog_web_app.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegisterDto registerDto) {
        UserEntity user = new UserEntity();
        user.setName(registerDto.getFirstName() + " " + registerDto.getLastName());
        user.setEmail(registerDto.getEmail());

        //use spring security to encrypt the password
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        RoleEntity role = roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
