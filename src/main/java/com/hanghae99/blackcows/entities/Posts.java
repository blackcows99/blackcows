package com.hanghae99.blackcows.entities;

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

    @OneToMany(targetEntity = Comment.class,mappedBy = "posts")
    private List<Comment> comment;


}
