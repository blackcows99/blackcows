package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.annotations.DeleteCache;
import com.hanghae99.blackcows.dto.PostDetailResponseDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.exceptions.PostException;
import com.hanghae99.blackcows.securities.MemberDetail;
import com.hanghae99.blackcows.services.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class PostsController {
    private List<SseEmitter> connections = new ArrayList<>();
    private final PostsService postsService;
    private final ApplicationEventPublisher publisher;
    @Autowired
    public PostsController(PostsService service,ApplicationEventPublisher publisher){
       this.postsService = service;
       this.publisher = publisher;
    }
    //게시글 작성 API
    @PostMapping("/api/post")
    @DeleteCache(key={PostFindResponseDto.class,PostDetailResponseDto.class})
    public void writePost(@RequestBody PostWriteRequestDto requestDto,@AuthenticationPrincipal MemberDetail user) throws PostException {
        postsService.save(requestDto,user.getMember());
    }

    //모든 게시글 조회 API
    @GetMapping("/api/posts")
    public List<PostFindResponseDto> findPosts() {
        return postsService.findAll();
    }

    //상세 페이지 조회 API
    @GetMapping("/api/post/{postId}")
    public PostDetailResponseDto findDetailPost(@PathVariable Long postId, @AuthenticationPrincipal MemberDetail user) {
        return postsService.findDetail(postId,user==null?null:user.getMember());
    }

    //게시글 수정 API
    @PatchMapping("/api/post/{postId}")
    @DeleteCache(key={PostFindResponseDto.class})
    public void updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto) throws PostException {
        postsService.update(postId, requestDto);
    }

    //게시물 삭제 API
    @DeleteMapping("/api/post/{postId}")
    @DeleteCache(key={PostFindResponseDto.class})
    public void deletePost(@PathVariable Long postId) {
        postsService.delete(postId);
    }
    @ExceptionHandler(IOException.class)
    public void handleAbort(){

    }
    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
