package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.dto.CommentDto;
import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping("/api/comment/{postid}")
    public Comment saveComment(@PathVariable("postid") Long postid, @RequestBody CommentDto commentDto){
        return service.saveComment(null,commentDto.createComment(),postid.longValue());
    }
    @DeleteMapping("/api/comment/{commentid}")
    public void deleteComment(@PathVariable("commentid") Long commentid){
        service.deleteComment(commentid.longValue());
    }
}
