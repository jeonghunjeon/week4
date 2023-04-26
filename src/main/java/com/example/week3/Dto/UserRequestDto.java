package com.example.week3.Dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserRequestDto {

    private String userName;
    private String passWord;
    private boolean admin;
    private String adminToken;
}
