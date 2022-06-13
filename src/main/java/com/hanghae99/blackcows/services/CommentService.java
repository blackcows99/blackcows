package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.entities.Posts;
import com.hanghae99.blackcows.exceptions.CommentException;
import com.hanghae99.blackcows.repositories.CommentRepository;
import com.hanghae99.blackcows.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepo;
    private final PostsRepository postRepo;
    @Transactional
    public Comment saveComment(Member m, Comment comment, long postId) throws CommentException {
        Posts posts = postRepo.findById(postId).orElseThrow(()->new IllegalArgumentException("요청한 데이터를 찾을 수 없습니다."));
        if(comment.getContent().length() < 1)
            throw new CommentException("댓글을 입력해 주세요.");
        comment.setMember(m);
        posts.addComment(comment);
        postRepo.save(posts);
        return comment;
    }

    @Transactional
    public void deleteComment(long commentid){
        Comment comment = commentRepo.findById(commentid).orElseThrow(()->new IllegalArgumentException("요청한 데이터를 찾을 수 없습니다."));
        Posts post = comment.getPosts();
        post.removeComment(comment);
        postRepo.save(post);
        commentRepo.delete(comment);
    }
}
