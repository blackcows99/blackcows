package com.hanghae99.blackcows.securities;

import com.hanghae99.blackcows.FinalValue;
import com.hanghae99.blackcows.entities.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class Oauth2AuthSuccessHanler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtTokenProvider provider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Member member = ((OAuth2User)authentication.getPrincipal()).getAttribute("member");
        String token = provider.generateToken(member);
        String targetUrl = determineTargetUrl(token);
        provider.setTokenHeader(token,response);
        log.info("redirect Url :" + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    //token을 생성하고 이를 포함한 프론트엔드로의 uri를 생성한다.
    protected String determineTargetUrl(String token) {
        String targetUri = FinalValue.REDIRECT_URL+"/?";
        return UriComponentsBuilder.fromUriString(targetUri).queryParam("member", token).build().toUriString();
    }

}
