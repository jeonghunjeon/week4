package com.example.week3.Controller;

import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Dto.UserRequestDto;
import com.example.week3.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blog/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public StatusResponseDto signUp(@RequestBody UserRequestDto userRequestDto) {
        return userService.signUp(userRequestDto);
    }

    @PostMapping("/login")
    public StatusResponseDto login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse servletResponse) {
        return userService.login(userRequestDto, servletResponse);
    }
}
