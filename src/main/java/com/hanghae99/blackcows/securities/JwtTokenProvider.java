package com.hanghae99.blackcows.securities;

import com.hanghae99.blackcows.entities.Member;
import com.hanghae99.blackcows.repositories.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = Base64.getEncoder().encodeToString("Secret Key".getBytes());
    private final long ValidTime = 1000L * 60 * 60;
    private MemberRepository repo;
    @Autowired
    public JwtTokenProvider(MemberRepository repo){
        this.repo = repo;
    }

    public void setTokenHeader(String token, HttpServletResponse response){
        response.setHeader("Authorization","Bearer "+token);
    }
    public String generateToken(Member m) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("loginId",m.getEmail());
        claims.put("name",m.getName());
        claims.put("id",m.getId());
        return doGenerateToken(claims, "id");
    }
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ValidTime))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }
    // Principal 반환
    public Authentication getAuthentication(MemberDetail userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    public MemberDetail getMemberDetail(String token){
        return new MemberDetail(repo.findById(Long.parseLong(this.getUserPk(token))).get());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get("id").toString();
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
