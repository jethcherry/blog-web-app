package com.blog_web_app.blog_web_app.service;


import com.blog_web_app.blog_web_app.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostDto> findAllPosts();

    void  createPost(PostDto postDto);

    PostDto findPostById(UUID id);

    void updatePost(PostDto postDto);

}
