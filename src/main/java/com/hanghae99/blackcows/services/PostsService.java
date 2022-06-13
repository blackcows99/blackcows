package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.*;
import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.repositories.CommentRepository;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;

    //포스트 저장
    @Transactional
    public void save(PostWriteRequestDto requestDto) {
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
    }

    //전체 포스트 가져오기
    @Transactional
    public List<PostFindResponseDto> findAll() {
        // TODO: 2022-06-12 : member 추가 해야함!!!
        List<PostFindResponseDto> responseDtos = new ArrayList<>();
        List<Posts> posts = postsRepository.findAll();

        for(Posts r : posts) {
            responseDtos.add(PostFindResponseDto.builder()
                    .id(r.getId())
                    .img(r.getImg())
                    .score(r.getScore())
                    .device(r.getDevice())
                    .contents(r.getContents())
                    .category(r.getCategory())
//                    .member(r.getMember().getName())
                    .build());
        }
        return responseDtos;
    }

    //상세페이지 조회
    @Transactional(readOnly = true)
    public PostDetailResponseDto findDetail(Long postId, Member member) {
        // TODO: 2022-06-12 : member추가, 편집 가능여부 추가해야함!!!!
        // TODO: 2022-06-12 : 디테일 DTO에서 List<Comment>를 넣는게 맞는가?? 
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        List<CommentDto> comments = new ArrayList<>();
        post.getComment().forEach(e->comments.add(new CommentDto(e,member)));

        return PostDetailResponseDto.builder()
                .id(post.getId())
                .img(post.getImg())
                .score(post.getScore())
                .device(post.getDevice())
                .contents(post.getContents())
                .category(post.getCategory())
                //.member(post.getMember().getName())
                .comments(comments)
                .build();
    }


    //게시글 수정하기
    @Transactional
    public void update(Long postId, PostUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다!"));
        post.update(requestDto);
    }

    //게시글 삭제하기
    @Transactional
    public void delete(Long postId) {
        postsRepository.deleteById(postId);
    }

}



