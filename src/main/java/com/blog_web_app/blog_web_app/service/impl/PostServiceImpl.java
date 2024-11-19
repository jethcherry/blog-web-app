package com.blog_web_app.blog_web_app.service.impl;

import com.blog_web_app.blog_web_app.dto.PostDto;
import com.blog_web_app.blog_web_app.entity.PostEntity;
import com.blog_web_app.blog_web_app.entity.UserEntity;
import com.blog_web_app.blog_web_app.mapper.PostMapper;
import com.blog_web_app.blog_web_app.repository.PostRepository;
import com.blog_web_app.blog_web_app.repository.UserRepository;
import com.blog_web_app.blog_web_app.service.PostService;
import com.blog_web_app.blog_web_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        UserEntity createdBy = userRepository.findByEmail(email);
        UUID userId = createdBy.getId();
        List<PostEntity> posts = postRepository.findPostByUser(userId);
        return posts.stream().map((post) -> PostMapper.mapToPostDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        UserEntity user = userRepository.findByEmail(email);
        PostEntity post = PostMapper.mapToPostEntity(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(UUID id) {
        PostEntity post = postRepository.findById(id).get();
        return PostMapper.mapToPostDto((post));
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        UserEntity createdBy = userRepository.findByEmail(email);
        PostEntity post = PostMapper.mapToPostEntity(postDto);
        post.setCreatedBy(createdBy);
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
