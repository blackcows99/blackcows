package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.PostDetailResponseDto;
import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.services.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    //게시글 작성 API
    @PostMapping("/api/post")
    public void writePost(@RequestBody PostWriteRequestDto requestDto) {
        postsService.save(requestDto);
    }

    //모든 게시글 조회 API
    @GetMapping("/api/posts")
    public List<PostFindResponseDto> findPosts() {
        return postsService.findAll();
    }

    //상세 페이지 조회 API
    @GetMapping("/api/post/{postId}")
    public PostDetailResponseDto findDetailPost(@PathVariable Long postId) {
        return postsService.findDetail(postId);
    }

    //게시글 수정 API
    @PatchMapping("/api/post/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) {
        log.info("requestDto = {}", requestDto);
        postsService.update(postId, requestDto);
    }

    //게시물 삭제 API
    @DeleteMapping("/api/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postsService.delete(postId);
    }

}
