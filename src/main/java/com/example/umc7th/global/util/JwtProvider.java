package com.example.umc7th.global.util;

import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.global.jwt.exception.AuthException;
import com.example.umc7th.global.jwt.exception.JwtErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private SecretKey secretKey; // JWT 서명에 사용되는 비밀 키
    private long accessExpiration; // 액세스 토큰의 만료 시간
    private long refreshExpiration; // 리프레시 토큰의 만료 시간

    // @Value: yml에서 해당 값을 가져오기 (아래의 YML의 값을 가져올 수 있음)
    public JwtProvider(@Value("${jwt.secret}") String secretKey,
                       @Value("${jwt.token.access-expiration-time}") long accessExpiration,
                       @Value("${jwt.token.refresh-expiration-time}") long refreshExpiration) {

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // 가져온 문자열로 SecretKey 생성
        this.accessExpiration = accessExpiration; // 액세스 토큰 만료 시간 설정
        this.refreshExpiration = refreshExpiration; // 리프레시 토큰 만료 시간 설정
    }

    public String createAccessToken(Member member) {
        return createToken(member, this.accessExpiration);
    }

    public String createRefreshToken(Member member) {
        return createToken(member, this.refreshExpiration);
    }

    public String createToken(Member member, long expiration) {

        Instant issuedAt = Instant.now(); // 만들어진 시간을 현재 시간으로
        Instant expiredAt = issuedAt.plusMillis(expiration); // 만들어진 시간에 시간을 추가해 만료일 생성
        return Jwts.builder()
                .setHeader(Map.of("alg", "HS256", "typ", "JWT")) // jwt 헤더 설정
                .setSubject(member.getEmail()) // jwt의 Subject을 email로 설정
                .claim("id", member.getId()) // id를 Claim으로 추가
                .setIssuedAt(Date.from(issuedAt)) // 만들어진 시간을 현재 시간으로 설정
                .setExpiration(Date.from(expiredAt)) // 유효 기간 설정
                .signWith(secretKey, SignatureAlgorithm.HS256) // 암호화 위한 sign 설정
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

    private Jws<Claims> getClaims(String token) {

        try {
            return Jwts.parserBuilder() // parsing 하기 위해 builder를 가져옴 -> 근데 parserBuilder() 12버전에는 없다(jjwt 버전 이유?)
                    .setSigningKey(secretKey) // sign key 설정
                    .build()
                    .parseClaimsJws(token); // claim 가져오기
        } catch (Exception e) { // parsing 과정 중 sign key가 틀리는 등의 이유로 일어나는 exception
            throw new AuthException(JwtErrorCode.INVALID_TOKEN);

            // INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401", "토큰이 유효하지 않습니다."),
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getBody().getSubject();
    }
}
