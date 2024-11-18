package com.example.umc7th.domain.oauth.service;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.domain.oauth.dto.OAuth2DTO;
import com.example.umc7th.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service {

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
    private final WebClient.Builder webClientBuilder;

    @Override
    public MemberResponseDTO.MemberTokenDTO kakaoLogin(String code) {

        // '워크북->OAuth2 Login->흐름'에서의 1~6 순서 기준
        // 4번
        OAuth2DTO.OAuth2TokenDTO tokenDTO = getAccessToken(code);
        // 5번
        OAuth2DTO.KakaoProfile profile = getProfileInfo(tokenDTO);
        // 6번
        return authenticateOrRegister(profile);
    }

    @Override
    // 인가코드 토큰 가져오기
    public OAuth2DTO.OAuth2TokenDTO getAccessToken(String code) {

        WebClient webClient = webClientBuilder.build();

        return webClient.post()
                .uri(tokenURI)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=authorization_code" +
                        "&client_id=" + clientId +
                        "&redirect_uri=" + redirectURI +
                        "&code=" + code)
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .block(); // 비동기
    }

    @Override
    // 토큰으로 사용자 정보 가져오기
    public OAuth2DTO.KakaoProfile getProfileInfo(OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO) {

        WebClient webClient = webClientBuilder.build();

        return webClient.get()
                .uri(userInfoURI)
                .headers(headers -> {
                    headers.set("Authorization", "Bearer " + oAuth2TokenDTO.getAccess_token());
                    headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                })
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .block();
    }

    @Override
    // 회원가입이 되어 있으면 사용자 로그인, 그렇지 않으면 회원가입 후 로그인 && JWT token 발급
    public MemberResponseDTO.MemberTokenDTO authenticateOrRegister(OAuth2DTO.KakaoProfile profile) {

        String email = profile.getKakao_account().getEmail();

        Member member = memberRepository.findByEmail(email).orElse(
                memberRepository.save(Member.builder()
                        .email(email)
                        .role("ROLE_USER")
                        .build())
        );

        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}
