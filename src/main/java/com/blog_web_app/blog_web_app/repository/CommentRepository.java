package com.blog_web_app.blog_web_app.repository;

import com.blog_web_app.blog_web_app.dto.CommentDto;
import com.blog_web_app.blog_web_app.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    @Query(value = "SELECT c.* FROM comments c INNER JOIN posts p\n" +
            "WHERE c.post_id AND p.created_by =:userId", nativeQuery = true)
    List<CommentEntity> findCommentByPost(UUID userId);

}
