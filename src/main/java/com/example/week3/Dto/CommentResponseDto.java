package com.example.week3.Dto;

import com.example.week3.Entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String userName;
    private String comment;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getCommentId();
        this.userName = comment.getUserName();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
