package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.PostFindRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    //게시글 작성 API
    @PostMapping("/api/post")
    public int write(@RequestBody PostWriteRequestDto requestDto) {
        postsService.save(requestDto);
        return 0;
    }

    //게시글 카테고리별 조회 API
    @GetMapping("/api/posts")
    public List<PostFindResponseDto> find(@RequestBody PostFindRequestDto requestDto) {
        return postsService.findPostsByCategory(requestDto);
    }
}
