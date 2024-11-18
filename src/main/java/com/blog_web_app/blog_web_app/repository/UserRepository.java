package com.blog_web_app.blog_web_app.repository;

import com.blog_web_app.blog_web_app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity , UUID> {
    UserEntity findByEmail(String email);
}
