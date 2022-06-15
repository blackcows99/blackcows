package com.hanghae99.blackcows.securities;

import com.hanghae99.blackcows.services.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity      //  Security 활성 어노테이션
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final OAuthLoginService loginService;

    private final Oauth2AuthSuccessHanler successHanler;

    private final JwtAuthFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()

                .and()
                .logout()   //  로그아웃 시 쿠키 정보 삭제
                .logoutUrl("/logout")   // ()로 로그아웃 요청
                .logoutSuccessUrl("http://localhost:3030/")  // 로그아웃 성공시 ()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID","member").permitAll()

                .and()
                .oauth2Login()      //  oauth2 로그인 설정
                .userInfoEndpoint()
                .userService(loginService)
                .and()
//                .defaultSuccessUrl("http://localhost:3000");  //  해당 서비스로 로긍니이 진행되도록 설정
                .successHandler(successHanler);

        return httpSecurity.build();
    }
}