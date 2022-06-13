package com.hanghae99.blackcows.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostFindResponseDto {
    private Long id;
    private String member;
    private String contents;
    private String img;
    private String date;
    private String device;
    private int category;
    private int score;
}
