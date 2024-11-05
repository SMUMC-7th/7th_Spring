package com.example.umc7th.global.jwt.util;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.global.jwt.exception.AuthException;
import com.example.umc7th.global.jwt.exception.JwtErrorCode;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private final SecretKey secretKey; //JWT 서명에 사용되는 비밀 키
    private final Long accessExpMs; //액세스 토큰의 만료 시간
    private final Long refreshExpMs; //리프레시 토큰의 만료 시간

    // @Value: yml에서 해당 값을 가져오기 (아래의 YML의 값을 가져올 수 있음)
    public JwtProvider(@Value("${spring.jwt.secret}") String secret,
                       @Value("${spring.jwt.token.access-expiration-time}") long accessExpiration,
                       @Value("${spring.jwt.token.refresh-expiration-time}") long refreshExpiration) {

        //주어진 시크릿 키 문자열을 바이트 배열로 변환하고, 이를 사용하여 SecretKey 객체 생성
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        accessExpMs = accessExpiration; // 액세스 토큰 만료 시간 설정
        refreshExpMs = refreshExpiration; // 리프레시 토큰 만료 시간 설정
    }

    public String createAccessToken(Member member) {
        return createToken(member, accessExpMs);
    }

    public String createRefreshToken(Member member) {
        return createToken(member, refreshExpMs);
    }

    public String createToken(Member member, long expiration) {
        Instant issuedAt = Instant.now(); // 만들어진 시간을 현재 시간으로
        Instant expiredAt = issuedAt.plusMillis(expiration); // 만들어진 시간에 시간을 추가해 만료일 만들기
        return Jwts.builder()
                .setHeader(Map.of("alg", "HS256", "typ", "JWT")) // JWT header 설정
                .setSubject(member.getEmail()) // JWT의 Subject을 email로 설정
                .claim("id", member.getId()) // id 를 Claim으로 추가
                .setIssuedAt(Date.from(issuedAt)) // 만들어진 시간을 현재 시간으로 설정
                .setExpiration(Date.from(expiredAt)) // 유효기간 설정
                .signWith(secretKey, SignatureAlgorithm.HS256) // 암호화를 위한 sign 설정
                .compact(); // 만들기
    }

    public boolean isValid(String token) {
        try {
            Jws<Claims> claims = getClaims(token); // Claim들 가져오기
            return claims.getBody().getExpiration().after(Date.from(Instant.now())); // 만료일이 지났는지 확인
        } catch (JwtException e) {
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            log.error(e.getMessage() + ": 토큰이 유효하지 않습니다.");
            return false;
        }
    }

    public Jws<Claims> getClaims(String token) {
        try {
            return Jwts.parser() // parsing 하기 위해 builder를 가져옴
                    .setSigningKey(secretKey) // sign key 설정
                    .build()
                    .parseClaimsJws(token); // claim 가져오기
        } catch (Exception e) { // parsing하는 과정에서 sign key가 틀리는 등의 이유로 일어나는 Exception
            throw new AuthException(JwtErrorCode.INVALID_TOKEN);
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getBody().getSubject();
    }

    // HTTP 요청의 'Authorization' 헤더에서 JWT 액세스 토큰을 검색
    public String resolveAccessToken(HttpServletRequest request) {
        log.info("[ JwtUtil ] 헤더에서 토큰을 추출합니다.");
        String tokenFromHeader = request.getHeader("Authorization");

        if (tokenFromHeader == null || !tokenFromHeader.startsWith("Bearer ")) {
            log.warn("[ JwtUtil ] Request Header 에 토큰이 존재하지 않습니다.");
            return null;
        }

        log.info("[ JwtUtil ] 헤더에 토큰이 존재합니다.");

        return tokenFromHeader.split(" ")[1]; //Bearer 와 분리
    }

}
