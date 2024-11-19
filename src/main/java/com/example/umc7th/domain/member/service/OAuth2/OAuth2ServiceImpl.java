package com.example.umc7th.domain.member.service.OAuth2;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service{

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI; // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI; // 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId; // API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI; // 설정한 Redirect uri

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
//    private final RestTemplate restTemplate;
    //restTemplate대신 WebClient 사용

    //응답요청과 응답 받아오는 것 분리=> 중복해결하여 성능개선
    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = fetchOAuth2Token(code);
        OAuth2DTO.KakaoProfile profile = fetchUserProfile(oAuth2TokenDTO.getAccess_token());

        String email = profile.getId().toString();
        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> memberRepository.save(Member.builder().email(email).role("ROLE_USER").build()));

        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

    private OAuth2DTO.OAuth2TokenDTO fetchOAuth2Token(String code) {
        return WebClient.create()
                .post()
                .uri(tokenURI)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("client_id", clientId)
                        .with("redirect_uri", redirectURI)
                        .with("code", code))
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL))
                .block(); // 동기적으로 응답을 기다림
    }

    private OAuth2DTO.KakaoProfile fetchUserProfile(String accessToken) {
        return WebClient.create()
                .get()
                .uri(userInfoURI)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded" + ";charset=utf-8")
                .header(AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL))
                .block(); // 동기적으로 응답을 기다림
    }
}
