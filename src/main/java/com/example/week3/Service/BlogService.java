package com.example.week3.Service;

import com.example.week3.Dto.BlogRequestDto;
import com.example.week3.Dto.BlogResponseDto;
import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Entity.Blog;
import com.example.week3.Entity.User;
import com.example.week3.Entity.UserRoleEnum;
import com.example.week3.Exception.CustomException;
import com.example.week3.Exception.ErrorCode;
import com.example.week3.Jwt.JwtUtils;
import com.example.week3.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final JwtUtils jwtUtils;
    public BlogResponseDto createBlog(BlogRequestDto blogRequestDto, HttpServletRequest servletRequest) {
        User user = jwtUtils.checkToken(servletRequest);
        Blog blog = new Blog(blogRequestDto, user);
        return new BlogResponseDto(blogRepository.save(blog));
    }

    public List<BlogResponseDto> getBlogList() {
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();

    }

    public BlogResponseDto getBlog(Long id) {
        Blog blog = checkBlog(id);
        return new BlogResponseDto(blog);
    }

    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto blogRequestDto, HttpServletRequest servletRequest) {
        Blog blog = checkBlog(id);
        User user = jwtUtils.checkToken(servletRequest);
        if (blog.getUserName().equals(user.getUserName()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            blog.update(blogRequestDto);
        } else {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
        return new BlogResponseDto(blog);
    }

    public StatusResponseDto deleteBlog(Long id, HttpServletRequest servletRequest) {
        Blog blog = checkBlog(id);
        User user = jwtUtils.checkToken(servletRequest);
        if (blog.getUserName().equals(user.getUserName()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            blogRepository.delete(blog);
            return new StatusResponseDto(200, "삭제에 성공하였습니다.");
        } else {
            throw new CustomException(ErrorCode.INVALID_USER);
        }
    }

    private Blog checkBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
                // 원래 NullPointerException을 사용했지만 IllegalArgumentException 이 상황에 더 알맞아서 변경.
        );
    }
}
