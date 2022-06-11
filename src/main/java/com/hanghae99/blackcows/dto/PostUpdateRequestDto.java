package com.hanghae99.blackcows.dto;

import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    private int category;
    private int score;
    private String contents;
    private String img;
    private String device;
}
