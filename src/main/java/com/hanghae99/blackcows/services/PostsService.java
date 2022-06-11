package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.PostRequestDto;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //포스트 저장 메소드
    public void save(PostRequestDto requestDto) {
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
    }
}
