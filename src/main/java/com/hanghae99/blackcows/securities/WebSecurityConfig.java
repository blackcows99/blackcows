package com.hanghae99.blackcows.securities;

import com.hanghae99.blackcows.services.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity      //  Security 활성 어노테이션
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final OAuthLoginService loginService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()      //  oauth2 로그인 설정
                .userInfoEndpoint()
                .userService(loginService)  //  해당 서비스로 로긍니이 진행되도록 설정
                .and()
                .logout()   //  로그아웃 시 쿠키 정보 삭제
                .logoutUrl("/logout")   // ()로 로그아웃 요청
                .logoutSuccessUrl("/")  // 로그아웃 성공시 ()
                .invalidateHttpsSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID").permitAll();
        return httpSecurity.build();
    }
}
