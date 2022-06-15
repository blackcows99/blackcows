package com.hanghae99.blackcows.securities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hanghae99.blackcows.configuration.AppProperties;
import com.hanghae99.blackcows.configuration.CookieOauth2Repository;
import com.hanghae99.blackcows.configuration.CookieUtils;
import com.hanghae99.blackcows.entities.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class Oauth2AuthSuccessHanler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final AppProperties appProperties;

    private final CookieOauth2Repository cookieRepo;

    private final JwtTokenProvider provider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//        String targetUrl = determineTargetUrl(request, response, authentication);
        Member member = ((OAuth2User)authentication.getPrincipal()).getAttribute("member");
        String token = provider.generateToken(member);
//        CookieUtils.addCookie(response,"member",token,2 * 360 * 1000);
//        clearAuthenticationAttributes(request, response);
        String targetUrl = determineTargetUrl(token);
        provider.setTokenHeader(token,response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    //token을 생성하고 이를 포함한 프론트엔드로의 uri를 생성한다.
    protected String determineTargetUrl(String token) {

        String targetUri = "http://localhost:3000?";
        return UriComponentsBuilder.fromUriString(targetUri).queryParam("member", token).build().toUriString();
    }

    //인증정보 요청 내역을 쿠키에서 삭제한다.
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        cookieRepo.removeAuthorizationRequestCookies(request, response);
    }

    //application.properties에 등록해놓은 Redirect uri가 맞는지 확인한다. (app.redirect-uris)
    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
}
