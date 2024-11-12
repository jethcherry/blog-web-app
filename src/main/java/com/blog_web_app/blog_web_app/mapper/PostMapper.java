package com.blog_web_app.blog_web_app.mapper;


import com.blog_web_app.blog_web_app.dto.PostDto;
import com.blog_web_app.blog_web_app.entity.PostEntity;

public class PostMapper {

    //map PostEntity to PostDto

    public static PostDto mapToPostDto(PostEntity post) {

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();

    }

    // PostDto to PostEntity

    public static PostEntity mapToPostEntity(PostDto postEntity) {

        return PostEntity.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .url(postEntity.getUrl())
                .url(postEntity.getContent())
                .content(postEntity.getContent())
                .shortDescription(postEntity.getShortDescription())
                .createdOn(postEntity.getCreatedOn())
                .updatedOn(postEntity.getUpdatedOn())
                .build();

    }

}
