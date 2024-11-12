package com.example.umc7th.domain.oauth2.service;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.domain.oauth2.OAuth2Properties;
import com.example.umc7th.domain.oauth2.dto.OAuth2DTO;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final OAuth2Properties oAuth2Properties;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // WebClient 사용해 Token URI 요청 및 응답 처리
        WebClient webClient = WebClient.builder().build();

        // Token 요청 설정
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = webClient.post()
                .uri(oAuth2Properties.getProvider().getKakao().getTokenUri())
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("client_id", oAuth2Properties.getRegistration().getKakao().getClientId())
                        .with("redirect_uri", oAuth2Properties.getRegistration().getKakao().getRedirectUri())
                        .with("code", code))
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .block();

        // 토큰을 이용해 사용자 정보 요청
        OAuth2DTO.KakaoProfile profile = webClient.get()
                .uri(oAuth2Properties.getProvider().getKakao().getUserInfoUri())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + oAuth2TokenDTO.getAccess_token())
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .block();

        // `email`로 회원 검색 및 가입 처리
        String email = profile.getKakao_account().getEmail(); // email로 변경하여 가져옴

        Member member = memberRepository.findByEmail(email).orElseGet(() ->
                memberRepository.save(
                        Member.builder()
                                .email(email)
                                .role("ROLE_USER")
                                .build()
                )
        );

        // JWT 토큰 생성 후 반환
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}