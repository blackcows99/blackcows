package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.securities.MemberDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class MemberController {
//    @GetMapping("/api/member")
//    public Member getLoginMember(@AuthenticationPrincipal OAuth2User user){
//        log.info(String.valueOf(user));
//        if(user != null)
//            return user.getAttribute("member");
//        else
//            return null;
//    }
    @GetMapping("/api/member")
    public Member getLoginMember(@AuthenticationPrincipal MemberDetail user){
        log.info(String.valueOf(user));
        if(user != null)
            return user.getMember();
        else
            return null;
    }
//    @GetMapping("/api/member")
//    public Member getLoginMember(HttpSession session) {
//        log.info(String.valueOf(session.getAttribute("member")));
//        if (session.getAttribute("member") != null)
//            return (Member) session.getAttribute("member");
//        else
//            return null;
//    }
}
