package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.PostRequestDto;
import com.hanghae99.blackcows.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    //게시글 작성 API
    @PostMapping("/api/post")
    public int write(@RequestBody PostRequestDto requestDto) {
        postsService.save(requestDto);
        return 0;
    }

}
