package com.example.week3.Service;

import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Dto.UserRequestDto;
import com.example.week3.Entity.User;
import com.example.week3.Entity.UserRoleEnum;
import com.example.week3.Exception.CustomException;
import com.example.week3.Exception.ErrorCode;
import com.example.week3.Jwt.JwtUtils;
import com.example.week3.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public StatusResponseDto signUp(UserRequestDto userRequestDto) {
        if (userRepository.findByUserName(userRequestDto.getUserName()).isPresent()) {
            throw new CustomException(ErrorCode.INVALID_USER_EXISTENCE);
        } else {
            if (userRequestDto.isAdmin()) {
                if (userRequestDto.getAdminToken() == null) {
                    throw new CustomException(ErrorCode.INVALID_TOKEN);
                } else {
                    if (userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                        userRepository.save(new User(userRequestDto, UserRoleEnum.ADMIN));
                    } else {
                        throw new CustomException(ErrorCode.INVALID_TOKEN);
                    }
                }
            } else {
                userRepository.save(new User(userRequestDto, UserRoleEnum.USER));
            }
        }return new StatusResponseDto(200, "회원가입 성공.");
    }

    public StatusResponseDto login(UserRequestDto userRequestDto, HttpServletResponse servletResponse) {
        User user = userRepository.findByUserName(userRequestDto.getUserName()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        if (!user.getPassWord().equals(userRequestDto.getPassWord())) {
            throw new CustomException(ErrorCode.INVALID_USER_PASSWORD);
        }
        servletResponse.addHeader(JwtUtils.AUTHORIZATION_HEADER, jwtUtils.createToken(user.getUserName(), user.getRole()));
        return new StatusResponseDto(200, "로그인 성공");
    }
}
