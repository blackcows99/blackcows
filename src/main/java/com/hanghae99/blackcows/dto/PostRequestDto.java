package com.hanghae99.blackcows.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostRequestDto {
    private int category;
    private int score;
    private String contents;
    private String img;
    private String device;
}
