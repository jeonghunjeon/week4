package com.example.week3.Controller;

import com.example.week3.Dto.CommentRequestDto;
import com.example.week3.Dto.CommentResponseDto;
import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blog/{blogId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public CommentResponseDto createComment(@PathVariable Long blogId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest servletRequest) {
        return commentService.createComment(blogId, commentRequestDto, servletRequest);
    }

    @PutMapping("/update/{id}")
    public CommentResponseDto updateComment(@PathVariable Long blogId, @PathVariable Long id,
                                            @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest servletRequest) {
        return commentService.updateComment(blogId, id, commentRequestDto, servletRequest);
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponseDto deleteComment(@PathVariable Long blogId, @PathVariable Long id, HttpServletRequest servletRequest) {
        return commentService.deleteComment(blogId, id, servletRequest);
    }
}
