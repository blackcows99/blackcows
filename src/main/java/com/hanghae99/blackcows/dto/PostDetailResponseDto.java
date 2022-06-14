package com.hanghae99.blackcows.dto;

import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.interfaces.ReturnDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailResponseDto implements ReturnDTO {
    private Long id;
    private int score;
    private int category;
    private String img;
    private String member;
    private String device;
    private String contents;
    private String date;
    private boolean isEditable;
    private List<CommentDto> comments;
}
