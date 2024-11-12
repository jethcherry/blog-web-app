package com.blog_web_app.blog_web_app.repository;

import com.blog_web_app.blog_web_app.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    Optional<PostEntity> findByUrl(String url);
}
