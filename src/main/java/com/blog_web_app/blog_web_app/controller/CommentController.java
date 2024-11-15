package com.blog_web_app.blog_web_app.controller;

import com.blog_web_app.blog_web_app.dto.CommentDto;
import com.blog_web_app.blog_web_app.service.CommentService;
import org.springframework.stereotype.Controller;  // Add @Controller annotation here
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller  // Indicates that this class is a Spring MVC controller
public class CommentController {

    private final CommentService commentService;

    // Constructor injection for CommentService
    public CommentController(CommentService commentService)  {
        this.commentService = commentService;
    }

    // Handler method to create form submit request for adding a comment
    @PostMapping("/{id}/comments")  // Mapping to POST request to add a comment for a specific post (identified by id)
    public String createComment(@PathVariable("id") UUID id,  // Extract post ID from the URL
                                @ModelAttribute("comment") CommentDto commentDto,  // Bind form data to CommentDto
                                Model model) {
        // Call the service layer to create a new comment
        commentService.createComment(id, commentDto);

        // Redirect to the post view after successfully adding the comment
        return "redirect:/post/" + id;  // Redirects to the post view using the post ID
    }
}
