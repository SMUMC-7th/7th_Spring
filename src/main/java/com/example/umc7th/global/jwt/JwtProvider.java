package com.example.umc7th.global.jwt;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.global.jwt.exception.AuthException;
import com.example.umc7th.global.jwt.exception.JwtErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private final SecretKey secret;
    private final Long accessExpiration;
    private final Long refreshExpiration;

    // @Value: yml에서 해당 값을 가져오기 (아래의 YML의 값을 가져올 수 있음)
    public JwtProvider(@Value("${Jwt.secret}") String secret, @Value("${Jwt.token.access-expiration-time}") Long accessExpiration, @Value("${Jwt.token.refresh-expiration-time}") Long refreshExpiration) {
        this.secret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // 가져온 문자열로 SecretKey 생성
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    public String createAccessToken(Member member) {
        return createToken(member, this.accessExpiration);
    }

    public String createRefreshToken(Member member) {
        return createToken(member, this.refreshExpiration);
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
                .signWith(secret, SignatureAlgorithm.HS256) // 암호화를 위한 sign 설정
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
                    .setSigningKey(secret) // sign key 설정
                    .build()
                    .parseClaimsJws(token); // claim 가져오기
        } catch (Exception e) { // parsing하는 과정에서 sign key가 틀리는 등의 이유로 일어나는 Exception
            throw new AuthException(JwtErrorCode.INVALID_TOKEN);
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getBody().getSubject();
    }

}