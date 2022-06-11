package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.PostFindResponseDto;
import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                    .device(r.getDevice())
                    .img(r.getImg())
                    .score(r.getScore())
                    .category(r.getCategory())
                    .build());
        }
        return responseDtos;
    }

    //게시글 수정하기
    @Transactional
    public void update(Long postId, PostUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다!"));
        post.update(requestDto);
    }

}



