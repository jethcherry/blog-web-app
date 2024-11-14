package com.blog_web_app.blog_web_app.repository;

import com.blog_web_app.blog_web_app.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    Optional<PostEntity> findByUrl(String url);

    @Query("SELECT p FROM PostEntity p WHERE p.title LIKE %:query% OR p.shortDescription LIKE %:query%")
    List<PostEntity> searchPost(@Param("query") String query);
}
