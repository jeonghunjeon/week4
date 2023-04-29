package com.example.week3.Controller;

import com.example.week3.Dto.CommentRequestDto;
import com.example.week3.Dto.CommentResponseDto;
import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Security.UserDetailsImpl;
import com.example.week3.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blog/{blogId}")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public CommentResponseDto createComment(@PathVariable Long blogId, @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(blogId, commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/update/{id}")
    public CommentResponseDto updateComment(@PathVariable Long blogId, @PathVariable Long id,
                                            @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(blogId, id, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponseDto deleteComment(@PathVariable Long blogId, @PathVariable Long id,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(blogId, id, userDetails.getUser());
    }
}
