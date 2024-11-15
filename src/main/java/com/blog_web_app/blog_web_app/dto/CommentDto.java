package com.blog_web_app.blog_web_app.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private UUID id;
    private String name;
    private String email;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
