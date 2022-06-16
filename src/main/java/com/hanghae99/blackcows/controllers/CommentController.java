package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.CommentDto;
import com.hanghae99.blackcows.exceptions.CommentException;
import com.hanghae99.blackcows.securities.MemberDetail;
import com.hanghae99.blackcows.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/api/comment/{postid}")
    public CommentDto saveComment(@PathVariable("postid") Long postid, @RequestBody CommentDto commentDto, @AuthenticationPrincipal MemberDetail user) throws CommentException {
        if(user == null)
            throw new AuthenticationCredentialsNotFoundException("로그인이 필요합니다.");
        CommentDto comment = new CommentDto(service.saveComment(user.getMember(),commentDto.createComment(),postid.longValue()),user.getMember());
        return comment;
    }
    @DeleteMapping("/api/comment/{commentid}")
    public void deleteComment(@PathVariable("commentid") Long commentid){
        service.deleteComment(commentid.longValue());
    }
    @ExceptionHandler
    public ResponseEntity handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
