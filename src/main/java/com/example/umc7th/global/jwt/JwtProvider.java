package com.example.umc7th.global.jwt;

import com.example.umc7th.global.jwt.error.AuthException;
import com.example.umc7th.global.jwt.error.JwtErrorCode;
import com.example.umc7th.member.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {

    private SecretKey secret;
    private long accessExpiration;
    private long refreshExpiration;

    // @Value: yml에서 해당 값을 가져오기 (아래의 YML의 값을 가져올 수 있음)
    public JwtProvider(@Value("${Jwt.secret}") String secret,
                       @Value("${Jwt.token.access-expiration-time}") long accessExpiration,
                       @Value("${Jwt.token.refresh-expiration-time}") long refreshExpiration) {
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
            return claims.getPayload().getExpiration().after(Date.from(Instant.now())); // 만료일이 지났는지 확인
        } catch (JwtException e) {
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            log.error(e.getMessage() + ": 토큰이 유효하지 않습니다.");
            return false;
        }
    }

    public Jws<Claims> getClaims(String token) { //토큰 claim을 가져온다.
        try {
            return Jwts.parser().verifyWith(secret)
                    .build().parseSignedClaims(token);
        } catch (Exception e) {
            throw new AuthException(JwtErrorCode.INVALID_TOKEN);
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getPayload().getSubject();
    }

}