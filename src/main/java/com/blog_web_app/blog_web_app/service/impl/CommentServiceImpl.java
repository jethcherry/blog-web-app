package com.blog_web_app.blog_web_app.service.impl;

import com.blog_web_app.blog_web_app.dto.CommentDto;
import com.blog_web_app.blog_web_app.entity.CommentEntity;
import com.blog_web_app.blog_web_app.entity.PostEntity;
import com.blog_web_app.blog_web_app.entity.UserEntity;
import com.blog_web_app.blog_web_app.mapper.CommentMapper;
import com.blog_web_app.blog_web_app.repository.CommentRepository;
import com.blog_web_app.blog_web_app.repository.PostRepository;
import com.blog_web_app.blog_web_app.repository.UserRepository;
import com.blog_web_app.blog_web_app.service.CommentService;
import com.blog_web_app.blog_web_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(UUID id, CommentDto commentDto) {
        PostEntity post = postRepository.findById(id).get();
        CommentEntity comment = CommentMapper.mapToCommentEntity(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {

        List<CommentEntity> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        UserEntity createdBy = userRepository.findByEmail(email);
        UUID userId = createdBy.getId();
        List<CommentEntity> comments = commentRepository.findCommentByPost(userId);
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }
}
