package com.example.week3.Dto;

import lombok.Getter;

@Getter
public class StatusResponseDto {
    private int statusCode;
    private String msg;

    public StatusResponseDto(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
