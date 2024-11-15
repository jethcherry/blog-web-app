package com.blog_web_app.blog_web_app.mapper;

import com.blog_web_app.blog_web_app.dto.CommentDto;
import com.blog_web_app.blog_web_app.entity.CommentEntity;

public class CommentMapper {
    //convert comment entity to comment dto

    public static CommentDto mapToCommentDto(CommentEntity comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .build();
    }

    //convert comment dto to comment entity
    public static CommentEntity mapToCommentEntity(CommentDto commentDto) {
        return CommentEntity.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content((commentDto.getContent()))
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdatedOn())
                .build();
    }
}
