package com.hanghae99.blackcows.securities;

import com.hanghae99.blackcows.configuration.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private JwtTokenProvider provider;

    @Autowired
    public JwtAuthFilter(JwtTokenProvider provider){
        this.provider=provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Cookie token = CookieUtils.getCookie(request,"member").orElse(null);
//        log.info("request Token:" + token==null?"No token":token.getValue());
//        String token = request.getHeader("Authentication");
        // 유효한 토큰인지 확인
        if (token != null) {
//            String jwtToken = token.replace("Bearer ","");
            log.info("token is not null");
            String jwtToken = token.getValue();
            if (provider.validateToken(jwtToken)) {
                // 토큰값과 refresh 토큰으로 유저 정보를 받아옴
                MemberDetail detail = provider.getMemberDetail(jwtToken);
                log.info("detail is null?:"+(detail.getMember() == null));
                if(detail.getMember() != null) {
                    Authentication authentication = provider.getAuthentication(detail);
                    // SecurityContext 에 Authentication 객체를 저장
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
