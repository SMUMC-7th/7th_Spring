package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final RestTemplate restTemplate;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // 1. 인가 코드를 사용하여 엑세스 토큰을 요청
        OAuth2DTO.OAuth2TokenDTO tokenDTO = requestAccessToken(code);

        // 2. 엑세스 토큰을 사용하여 카카오 사용자 정보를 가져옴
        OAuth2DTO.KakaoProfile profile = requestKakaoProfile(tokenDTO.getAccess_token());

        // 3. 사용자 이메일 확인 및 회원가입/로그인 처리
        String email = profile.getKakao_account().getEmail();
        if (email == null) {
            throw new MemberException(MemberErrorCode.EMAIL_NOT_FOUND); // 이메일 없으면 예외 발생
        }

        Member member = memberRepository.findByEmail(email).orElseGet(() ->
            memberRepository.save(Member.builder()
                .email(email)
                .role("ROLE_USER")
                .build())
        );

        // 4. JWT 토큰 발급
        return MemberResponseDTO.MemberTokenDTO.builder()
            .accessToken(jwtProvider.createAccessToken(member))
            .refreshToken(jwtProvider.createRefreshToken(member))
            .build();
    }

    private OAuth2DTO.OAuth2TokenDTO requestAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectURI);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(tokenURI, HttpMethod.POST, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL); // 토큰 요청 실패 시 예외 발생
        }
    }

    private OAuth2DTO.KakaoProfile requestKakaoProfile(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(userInfoURI, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL); // 사용자 정보 요청 실패 시 예외 발생
        }
    }
}