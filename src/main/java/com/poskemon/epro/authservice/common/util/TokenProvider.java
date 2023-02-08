package com.poskemon.epro.authservice.common.util;

import com.poskemon.epro.authservice.domain.entity.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * JWT를 생성하고 검증하는 클래스
 *
 * @author isohyeon
 */
@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "1UQJ5XsXc8ZtKZdfqsJZmrVEyHH4jvoJpmzkEsIjqki4F9HwxWSuwnjuwkKxtcFcNKM9gLhAPm3TfhJxYEEfoNvUQlLvnnE5JrlQ";

    /**
     * JWT Token 생성 메서드
     * 유저 정보를 바탕으로 헤더와 페이로드를 작성하고 전자 서명한 후 토큰을 반환한다.
     *
     * @param auth JWT Token 생성을 위한 유저 정보를 담은 엔티티
     * @return JWT Token
     */
    public String create(Auth auth) {
        // 기한 지금으로부터 1일
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        // JWT 생성
        return Jwts.builder()
                   // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                   .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                   // payload에 들어갈 내용
                   .setSubject(auth.getEmail())
                   .setIssuer("poskemon")
                   .setIssuedAt(new Date())
                   .setExpiration(expiryDate)
                   .compact();
    }

    /**
     * JWT Token 검증(디코딩, 파싱 및 위조여부 확인) 메서드
     *
     * @param token 검증하려는 JWT Token
     * @return 검증된 email(subject) 또는 예외
     */
    public String validateAndGetUserId(String token) {
        // parseClaimsJws 메서드가 Base 64로 디코딩 및 파싱
        // 즉, 헤더와 페이로드를 setSigningKey로 넘어온 비밀키를 이용해 서명 후, token의 서명과 비교.
        // 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        Claims claims = Jwts.parser()
                            .setSigningKey(SECRET_KEY)
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }
}
