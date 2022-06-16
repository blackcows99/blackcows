package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.securities.MemberDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MemberController {
    @GetMapping("/api/member")
    public Member getLoginMember(@AuthenticationPrincipal MemberDetail user){
        log.info("user request");
        if(user != null) {
            log.info(user.getMember().getEmail());
            return user.getMember();
        }else
            return null;
    }
}
