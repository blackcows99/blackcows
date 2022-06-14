package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.annotations.DeleteCache;
import com.hanghae99.blackcows.annotations.UseCache;
import com.hanghae99.blackcows.dto.PostDetailResponseDto;
import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.exceptions.PostException;
import com.hanghae99.blackcows.services.PostsService;
import com.hanghae99.blackcows.services.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    //게시글 작성 API
    @PostMapping("/api/post")
    @DeleteCache(key={PostFindResponseDto.class,PostDetailResponseDto.class})
    public void writePost(@RequestBody PostWriteRequestDto requestDto,@AuthenticationPrincipal OAuth2User user) throws PostException {
        postsService.save(requestDto,user.getAttribute("member"));
    }

    //모든 게시글 조회 API
    @GetMapping("/api/posts")
    @UseCache(key = PostFindResponseDto.class)
    public List<PostFindResponseDto> findPosts() {
        return postsService.findAll();
    }

    //상세 페이지 조회 API
    @GetMapping("/api/post/{postId}")
    public PostDetailResponseDto findDetailPost(@PathVariable Long postId, @AuthenticationPrincipal OAuth2User user) {
        return postsService.findDetail(postId,user.getAttribute("member"));
    }

    //게시글 수정 API
    @PatchMapping("/api/post/{postId}")
    @DeleteCache(key={PostFindResponseDto.class})
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) throws PostException {
        log.info("requestDto = {}", requestDto);
        postsService.update(postId, requestDto);
    }

    //게시물 삭제 API
    @DeleteMapping("/api/post/{postId}")
    @DeleteCache(key={PostFindResponseDto.class,PostDetailResponseDto.class})
    public void deletePost(@PathVariable Long postId) {
        postsService.delete(postId);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
