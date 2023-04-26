package com.example.week3.Service;

import com.example.week3.Dto.CommentRequestDto;
import com.example.week3.Dto.CommentResponseDto;
import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Entity.Blog;
import com.example.week3.Entity.Comment;
import com.example.week3.Entity.User;
import com.example.week3.Entity.UserRoleEnum;
import com.example.week3.Exception.CustomException;
import com.example.week3.Exception.ErrorCode;
import com.example.week3.Jwt.JwtUtils;
import com.example.week3.Repository.BlogRepository;
import com.example.week3.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final JwtUtils jwtUtils;

    public CommentResponseDto createComment(Long blogId, CommentRequestDto commentRequestDto, HttpServletRequest servletRequest) {

        User user = jwtUtils.checkToken(servletRequest);
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new CustomException(ErrorCode.BLOG_NOT_FOUND)
        );
        Comment comment = new Comment(blog, commentRequestDto, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long blogId, Long id, CommentRequestDto commentRequestDto, HttpServletRequest servletRequest) {
        User user = jwtUtils.checkToken(servletRequest);
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new CustomException(ErrorCode.BLOG_NOT_FOUND)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
        if (user.getUserName().equals(comment.getUserName()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            comment.update(commentRequestDto);
        } else {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
        return new CommentResponseDto(comment);
    }

    public StatusResponseDto deleteComment(Long blogId, Long id, HttpServletRequest servletRequest) {
        User user = jwtUtils.checkToken(servletRequest);
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new CustomException(ErrorCode.BLOG_NOT_FOUND)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
        if (user.getUserName().equals(comment.getUserName()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            commentRepository.delete(comment);
        } else {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
        return new StatusResponseDto(200, "삭제 성공");
    }
}
