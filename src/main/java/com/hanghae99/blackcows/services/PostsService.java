package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.*;
import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.exceptions.PostException;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    //포스트 저장
    @Transactional
    public void save(PostWriteRequestDto requestDto,Member member) throws PostException {
        if(requestDto.getCategory() <1)
            throw new PostException("카타고리를 선택해 주세요");
        else if (requestDto.getDevice()==null || requestDto.getDevice().length()<1)
            throw new PostException("장치 이름을 입력해 주세요");
        else if(requestDto.getImg() == null||requestDto.getImg().length() < 1)
            throw new PostException("이미지를 선택해 주세요");
        else if(requestDto.getContents().length() < 1 || requestDto.getContents() == null)
            throw new PostException("내용을 입력해 주세요.");
        Posts posts = new Posts(requestDto);
        posts.setMember(member);
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
                    .date(r.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .category(r.getCategory())
                    .member(r.getMember().getName())
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
        if(member != null)
            System.out.println("is editable:" + (post.getMember().getId() == member.getId()));
        Collections.reverse(comments);
        return PostDetailResponseDto.builder()
                .id(post.getId())
                .img(post.getImg())
                .score(post.getScore())
                .device(post.getDevice())
                .contents(post.getContents())
                .category(post.getCategory())
                .isEditable(member==null?false:post.getMember().getId() == member.getId())
                .date(post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .member(post.getMember().getName())
                .comments(comments)
                .build();
    }


    //게시글 수정하기
    @Transactional
    public void update(Long postId, PostUpdateRequestDto requestDto) throws PostException {
        System.out.println(requestDto.getCategory());
        if(requestDto.getCategory() <1)
            throw new PostException("카타고리를 선택해 주세요");
        else if (requestDto.getDevice()==null || requestDto.getDevice().length()<1)
            throw new PostException("장치 이름을 입력해 주세요");
        else if(requestDto.getImg() == null||requestDto.getImg().length() < 1)
            throw new PostException("이미지를 선택해 주세요");
        else if(requestDto.getContents().length() < 1 || requestDto.getContents() == null)
            throw new PostException("내용을 입력해 주세요.");
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



