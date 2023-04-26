package com.example.week3.Entity;

import com.example.week3.Dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Blog> blogs = new ArrayList<Blog>();
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=\\S+$).{4,10}",
            message = "아이디는 알파벳 소문자, 숫자를 입력하고 4~10자리로 구성해주세요.")
    private String userName;
    @Column(nullable = false)
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[~`!@#$%\\\\^&*()-])(?=\\S+$).{8,15}",
            message = "비밀번호는 알파벳 대소문자, 숫자를 입력하고 8~15자리로 구성해주세요.")
    private String passWord;
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User (UserRequestDto requestDto, UserRoleEnum role) {
        this.userName = requestDto.getUserName();
        this.passWord = requestDto.getPassWord();
        this.role = role;
    }
}
