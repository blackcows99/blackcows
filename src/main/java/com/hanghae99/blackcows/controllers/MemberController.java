package com.hanghae99.blackcows.controllers;

import com.hanghae99.blackcows.entities.Member;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/api/member")
    public Member getLoginMember(@AuthenticationPrincipal OAuth2User user){
        if(user != null)
            return user.getAttribute("member");
        else
            return null;
    }
}
