package com.hanghae99.blackcows.dto;

import com.hanghae99.blackcows.entities.Comment;
import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.interfaces.ReturnDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@Getter
public class CommentDto implements ReturnDTO {
    private Long id;
    private String member;
    private String comment;
    private boolean isDeleteable;
    public CommentDto(Comment comment, Member member){
        this.id = comment.getId();
        this.member = comment.getMember().getName();
        this.comment = comment.getContent();
        this.isDeleteable = member == null? false : member.getId() == comment.getMember().getId();
    }
    public Comment createComment(){
        Comment c = new Comment();
        c.setContent(this.comment);
        return c;
    }
}
