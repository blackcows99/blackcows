package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.entities.Category;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //포스트 저장
    public void save(PostWriteRequestDto requestDto) {
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
    }

    //전체 포스트 가져오기
    public List<PostFindResponseDto> findAll() {

        List<PostFindResponseDto> responseDtos = new ArrayList<>();
        List<Posts> posts = postsRepository.findAll();

        for(Posts r : posts) {
            responseDtos.add(PostFindResponseDto.builder()
                    .id(r.getId())
                    .contents(r.getContents())
//                    .member(r.getMember().getName())
                    .img(r.getImg())
                    .score(r.getScore())
                    .category(r.getCategory())
                    .build());
        }
        return responseDtos;
    }


}
