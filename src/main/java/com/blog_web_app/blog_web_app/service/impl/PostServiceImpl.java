package com.blog_web_app.blog_web_app.service.impl;

import com.blog_web_app.blog_web_app.dto.PostDto;
import com.blog_web_app.blog_web_app.entity.PostEntity;
import com.blog_web_app.blog_web_app.mapper.PostMapper;
import com.blog_web_app.blog_web_app.repository.PostRepository;
import com.blog_web_app.blog_web_app.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        PostEntity post = PostMapper.mapToPostEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(UUID id) {
        PostEntity post = postRepository.findById(id).get();
        return PostMapper.mapToPostDto((post));
    }

    @Override
    public void updatePost(PostDto postDto) {
        PostEntity post = PostMapper.mapToPostEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public void deletePosts(UUID id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDto findPostByUrl(String url) {
        PostEntity post = postRepository.findByUrl(url).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<PostEntity> posts = postRepository.searchPost(query);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }
}
