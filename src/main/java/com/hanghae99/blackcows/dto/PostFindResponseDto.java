package com.hanghae99.blackcows.dto;

import com.hanghae99.blackcows.entities.Member;
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
    private Member member;
    private String contents;
    private String img;
    private String category;
    private int score;
}
