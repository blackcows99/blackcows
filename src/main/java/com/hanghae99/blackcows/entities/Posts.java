package com.hanghae99.blackcows.entities;

import com.hanghae99.blackcows.dto.PostUpdateRequestDto;
import com.hanghae99.blackcows.dto.PostWriteRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Setter
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

    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    public Posts(PostWriteRequestDto requestDto) {
        this.device = requestDto.getDevice();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.score = requestDto.getScore();
        this.img = requestDto.getImg();
    }

    public void update(PostUpdateRequestDto requestDto) {
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
