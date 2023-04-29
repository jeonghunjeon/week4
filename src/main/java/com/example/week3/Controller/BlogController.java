package com.example.week3.Controller;

import com.example.week3.Dto.BlogRequestDto;
import com.example.week3.Dto.BlogResponseDto;
import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Repository.BlogRepository;
import com.example.week3.Security.UserDetailsImpl;
import com.example.week3.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/create")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto blogRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.createBlog(blogRequestDto, userDetails.getUser());
    }

    @GetMapping("/list")
    public List<BlogResponseDto> getBlogList() {
        return blogService.getBlogList();
    }

    @GetMapping("/{id}")
    public BlogResponseDto getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/update/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.updateBlog(id, blogRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/delete/{id}")
    public StatusResponseDto deleteBlog(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.deleteBlog(id, userDetails.getUser());
    }
}
