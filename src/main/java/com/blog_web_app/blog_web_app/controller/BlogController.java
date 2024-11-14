package com.blog_web_app.blog_web_app.controller;

import com.blog_web_app.blog_web_app.dto.PostDto;
import com.blog_web_app.blog_web_app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class BlogController {

    private PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    // handler method to handle http://localhost:8080/ request

    @GetMapping("/")
    public String viewBlogPosts(Model model) {
        List<PostDto> postResponse = postService.findAllPosts();
        model.addAttribute("postResponse", postResponse);
        return "blog/view_posts";
    }

    //handler method to handle view post request
    @GetMapping("/post/{id}")
    private String showPost(@PathVariable("id") UUID id, Model model) {
        PostDto post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "blog/blog_post";
    }

    //handler method  to handle blog post search request.
    //http://localhost:8080/page/search?query=java
    @GetMapping("/page/search")
    public String searchPost(@RequestParam(value = "query") String query, Model model) {

        List<PostDto> postsResponse = postService.searchPosts(query);
        model.addAttribute("postResponse", postsResponse);
        return "blog/view_posts";

    }

}
