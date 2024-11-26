package com.example.umc7th.domain.oauth.service;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.domain.oauth.dto.OAuth2DTO;
import com.example.umc7th.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
    private final WebClient.Builder webClientBuilder;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String code) {
        // 인가코드 토큰 가져오기
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = getOAuth2TokenDTOFromKakao(code);

        // 토큰으로 정보 가져오기
        OAuth2DTO.KakaoProfile profile = getKakaoProfileUseToken(oAuth2TokenDTO);

        // 회원가입이 되었으면 사용자 로그인 안되어있으면 회원가입 후 로그인
        Member member = fetchOrCreateMember(profile);

        // TokenDTO로 변경해서 저번 주차에 구현한 JWT 형태로 반환
        return getTokenDTOFromMember(member);
    }

    private OAuth2DTO.OAuth2TokenDTO getOAuth2TokenDTOFromKakao(String code) {
        WebClient webClient = webClientBuilder.build();

        return webClient.post()
                .uri(tokenURI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue("grant_type=authorization_code&client_id=" + clientId + "&redirect_uri=" + redirectURI + "&code=" + code)
                .retrieve()
                .bodyToMono(OAuth2DTO.OAuth2TokenDTO.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL))
                .block();
    }

    private OAuth2DTO.KakaoProfile getKakaoProfileUseToken(OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO) {
        WebClient webClient = webClientBuilder.build();

        return webClient.get()
                .uri(userInfoURI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + oAuth2TokenDTO.getAccess_token())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(OAuth2DTO.KakaoProfile.class)
                .onErrorMap(e -> new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL))
                .block();
    }

    private Member fetchOrCreateMember(OAuth2DTO.KakaoProfile profile) {
        String email = profile.getKakao_account().getEmail().trim(); // Kakao에서의 Id를 가지고 Email로 변경

        // email을 찾고 있으면 member에 넣고 없으면 새로 만들어서 저장하고 넣는다.
        return memberRepository.findByEmail(email).orElseGet(() ->
                memberRepository.save(Member.builder()
                        .email(email)
                        .role("ROLE_USER")
                        .build())
        );
    }

    private MemberResponseDTO.MemberTokenDTO getTokenDTOFromMember(Member member) {
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}

