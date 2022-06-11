package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.PostFindRequestDto;
import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.entities.Category;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final Category category;

    //포스트 저장 메소드
    public void save(PostWriteRequestDto requestDto) {
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
    }

    //카테고리별 포스트 가져오기 메소드
    public List<PostFindResponseDto> findPostsByCategory(PostFindRequestDto requestDto) {

        List<PostFindResponseDto> responseDtos = new ArrayList<>();
        List<Posts> posts = new ArrayList<>();

        if (requestDto.getCategory() == 0) {
            posts = postsRepository.findAll();
        }else {
            posts = postsRepository.findAllByCategory(requestDto.getCategory());
        }

        posts.stream()
                .forEach(r ->
                        responseDtos.add(PostFindResponseDto.builder()
                                .id(r.getId())
                                .contents(r.getContents())
                                .member(r.getMember())
                                .img(r.getImg())
                                .score(r.getScore())
                                .category(category.getName(r.getCategory()))
                                .build()));

        return responseDtos = postsRepository.findAll()
                .stream()
                .map(r ->
                     PostFindResponseDto.builder()
                             .id(r.getId())
                             .contents(r.getContents())
                             .member(r.getMember())
                             .img(r.getImg())
                             .score(r.getScore())
                             .category(category.getName(r.getCategory()))
                             .build())
                .collect(Collectors.toList());
    }
}
