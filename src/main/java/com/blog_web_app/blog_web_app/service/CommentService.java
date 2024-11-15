package com.blog_web_app.blog_web_app.service;

import com.blog_web_app.blog_web_app.dto.CommentDto;

import java.util.UUID;

public interface CommentService {
    void createComment(UUID id, CommentDto commentDto);

}
