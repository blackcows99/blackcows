package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.PostFindRequestDto;
import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    //게시글 작성 API
    @PostMapping("/api/post")
    public void writePost(@RequestBody PostWriteRequestDto requestDto) {
        postsService.save(requestDto);
    }

    //게시글 카테고리별 조회 API
    @GetMapping("/api/posts")
    public List<PostFindResponseDto> findPosts(@RequestBody PostFindRequestDto requestDto) {
        return postsService.findPostsByCategory(requestDto);
    }

    //게시글 수정 API
    @PatchMapping("/api/post/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        postsService.update(requestDto);
    }
}
