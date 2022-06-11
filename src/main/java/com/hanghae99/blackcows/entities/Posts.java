package com.hanghae99.blackcows.entities;

import com.hanghae99.blackcows.dto.PostRequestDto;
import com.hanghae99.blackcows.repositories.CommentRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends TimeStamp {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(nullable = false)
    private String device;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String img;

    @Column(columnDefinition = "integer default 0")
    private int category;

    @Column(nullable = false)
    private int score;

    @ManyToOne(targetEntity = Member.class ,fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(targetEntity = Comment.class,mappedBy = "posts",cascade = CascadeType.ALL)
    private List<Comment> comment;

    public Posts(PostRequestDto requestDto) {
        this.device = requestDto.getDevice();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.score = requestDto.getScore();
        this.img = requestDto.getImg();
    }

    public void addComment(Comment comment){
        this.comment.add(comment);
    }

    public void removeComment(Comment comment){
        this.comment.remove(comment);
    }
}
