package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.annotations.DeleteCache;
import com.hanghae99.blackcows.dto.CommentDto;
import com.hanghae99.blackcows.dto.PostDetailResponseDto;
import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.exceptions.CommentException;
import com.hanghae99.blackcows.securities.MemberDetail;
import com.hanghae99.blackcows.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/api/comment/{postid}")
    @DeleteCache(key = {PostDetailResponseDto.class})
    public Comment saveComment(@PathVariable("postid") Long postid, @RequestBody CommentDto commentDto, @AuthenticationPrincipal MemberDetail user) throws CommentException {
        return service.saveComment(user.getMember(),commentDto.createComment(),postid.longValue());
    }
    @DeleteMapping("/api/comment/{commentid}")
    @DeleteCache(key = {PostDetailResponseDto.class})
    public void deleteComment(@PathVariable("commentid") Long commentid){
        service.deleteComment(commentid.longValue());
    }
    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
