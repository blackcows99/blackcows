package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.CommentDto;
import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/api/comment/{postid}")
    public ResponseEntity saveComment(@PathVariable("postid") Long postid, @RequestBody CommentDto commentDto, @AuthenticationPrincipal OAuth2User user){
        return ResponseEntity.ok(service.saveComment(user.getAttribute("member"),commentDto.createComment(),postid.longValue()));
    }
    @DeleteMapping("/api/comment/{commentid}")
    public void deleteComment(@PathVariable("commentid") Long commentid){
        service.deleteComment(commentid.longValue());
    }
}
