package com.hanghae99.blackcows.services;

import com.hanghae99.blackcows.dto.MemberRequestDto;
import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuthLoginService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository repository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User user = delegate.loadUser(userRequest);   //  OAuth Server에 사용자 요청

        // OAuth2 로그인 진행시 키가 되는 필드값
        // 네이버, 카카오등은 지원하지 않는다.
        // OAuth2User의 정보를 생성할때 Key값으로 쓰인다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 사용자 정보 값
        // Google 기준으로 범위에서 설정한 값이 제공된다.
        // System.out.println을 통해 사이트별 들어오는 정보를 확인할 수 있다.
        Map<String, Object> copy = new HashMap<>();
        Map<String,Object> attr = user.getAttributes();
        for (String key : attr.keySet()) {
            copy.put(key,attr.get(key));
        }

        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setName(((Map<String,Object>)copy.get("properties")).get("nickname").toString());
        requestDto.setEmail(((Map<String,Object>)copy.get("kakao_account")).get("email").toString());
        // 정보가 없으면 가입
        Member member = registerIfNeeded(requestDto);
        copy.put("member",member);
        // 인증 객체를 만들어서 return 한다.
        // Collections.emptyList()는 권한 목록이다.
        return new DefaultOAuth2User(Collections.emptyList(), copy, userNameAttributeName);
    }

    // DB 유무 확인
    private Member registerIfNeeded(MemberRequestDto requestDto) {
        String email = requestDto.getEmail();
        Member member = repository.findByEmail(email).orElse(null);
        if (member == null) {
            String name = requestDto.getName();
            member = new Member(name, email);
        }else{
            member.setName(requestDto.getName());
        }
        return repository.save(member);
    }
}