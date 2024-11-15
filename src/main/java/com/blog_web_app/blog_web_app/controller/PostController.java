package com.blog_web_app.blog_web_app.controller;


import com.blog_web_app.blog_web_app.dto.CommentDto;
import com.blog_web_app.blog_web_app.dto.PostDto;
import com.blog_web_app.blog_web_app.service.CommentService;
import com.blog_web_app.blog_web_app.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    //Create a handler method, Get request and a model and view
    @GetMapping("/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "admin/posts";

    }

    //handler method to handle list comments request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model) {
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";

    }

    //handle method to handle new post request
    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create-post";
    }

    //Handler method to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {


        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/create-post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";

    }

    //handler method to handle edit post request
    @GetMapping("/admin/posts/{id}/edit")
    public String editPostForm(@PathVariable("id") UUID id, Model model) {
        PostDto postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    //handler method to handle edit post form post request
    @PostMapping("/admin/posts/{id}")
    public String updatePost(@PathVariable("id") UUID id, @Valid @ModelAttribute("post") PostDto post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "admin/edit_post";
        }
        post.setId(id);
        postService.updatePost(post);
        return "redirect:/admin/posts";

    }

    //handler method to handle deletePost request
    @GetMapping("/admin/posts/{id}/delete")
    public String deletePost(@PathVariable("id") UUID id) {
        postService.deletePosts(id);
        return "redirect:/admin/posts";

    }

    //handler method to hande view post request
    @GetMapping("/admin/posts/{id}/view")
    public String viewPost(@PathVariable("id") UUID id, Model model) {
        PostDto postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    //handler method to handle to search blog post request
    //url-localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";

    }

    //handlet method


    public static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;

    }


}
