package com.example.umc7th.domain.oauth2.service;

import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.domain.oauth2.dto.OAuth2Dto;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final WebClient webClient = WebClient.builder().build();

    @Override
    public MemberResDto.MemberTokenDto login(String code) {
        try {
            // 1. Access Token 요청
            System.out.println("Access Token 요청 중...");
            OAuth2Dto.OAuth2TokenDto tokenDto = getAccessToken(code);

            // 2. 사용자 정보 요청
            System.out.println("사용자 정보 요청 중...");
            OAuth2Dto.KakaoProfile kakaoProfile = getUserInfo(tokenDto.getAccess_token());

            // 3. 이메일 확인
            String email = kakaoProfile.getKakao_account().getEmail();
            System.out.println("이메일 확인: " + email);
            if (email == null) {
                throw new MemberException(MemberErrorCode.OAUTH_EMAIL_NOT_FOUND);
            }

            // 4. 데이터베이스에서 사용자 조회 또는 신규 사용자 저장
            System.out.println("사용자 조회 또는 저장 중...");
            Member member = memberRepository.findByEmail(email)
                    .orElseGet(() -> memberRepository.save(
                            Member.builder()
                                    .email(email)
                                    .role("ROLE_USER")
                                    .build()));

            // 5. JWT 토큰 발급
            System.out.println("JWT 토큰 발급 중...");
            return MemberResDto.MemberTokenDto.builder()
                    .accessToken(jwtProvider.createAccessToken(member))
                    .refreshToken(jwtProvider.createRefreshToken(member))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new MemberException(MemberErrorCode.OAUTH_LOGIN_FAIL);
        }
    }


    @Override
    public OAuth2Dto.OAuth2TokenDto getAccessToken(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", clientId);
        formData.add("redirect_uri", redirectURI);
        formData.add("code", code);
        formData.add("client_secret", clientSecret);

        return webClient.post()
                .uri(tokenURI)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(OAuth2Dto.OAuth2TokenDto.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL))
                .block();
    }

    @Override
    public OAuth2Dto.KakaoProfile getUserInfo(String accessToken) {
        return webClient.get()
                .uri(userInfoURI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(OAuth2Dto.KakaoProfile.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL)) // 에러 처리
                .block();
    }
}
