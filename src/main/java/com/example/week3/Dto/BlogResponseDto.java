package com.example.week3.Dto;

import com.example.week3.Entity.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BlogResponseDto {
    private String title;
    private String userName;
    private String content;
    private List<CommentResponseDto> comments;
    private LocalDateTime createdAt;

    public BlogResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.userName = blog.getUserName();
        this.content = blog.getContent();
        this.comments = blog.getComments().stream().map(CommentResponseDto::new).sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()).collect(Collectors.toList());
        this.createdAt = blog.getCreatedAt();
    }
}
