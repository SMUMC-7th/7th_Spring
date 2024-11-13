package com.example.umc7th.service.command;

import com.example.umc7th.dto.OAuth2Dto;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.entity.Member;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.example.umc7th.repository.MemberRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public String getAccessTokenFromKakao(String code) {
        OAuth2Dto.OAuth2TokenDto oAuth2Dto = WebClient.create()
                .post()
                .uri(tokenURI, uriBuilder -> uriBuilder
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectURI)   // 필수 파라미터
                        .queryParam("code", code)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(OAuth2Dto.OAuth2TokenDto.class)
                .doOnError(error -> log.error("Error occurred while getting token: ", error)) // 에러 로깅
                .block();
        log.info(oAuth2Dto.getAccess_token());

        return oAuth2Dto.getAccess_token();

    }

    @Override
    public OAuth2Dto.KakaoProfile getUserInfo(String accessToken) {
        OAuth2Dto.KakaoProfile userInfo = WebClient.create(userInfoURI)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(OAuth2Dto.KakaoProfile.class)
                .block();

        return userInfo;
    }

    @Override
    public MemberResponseDto.LoginResponseDto login(OAuth2Dto.KakaoProfile userInfo) {
        String email = userInfo.getKakao_account().getEmail();
        // email을 찾고 있으면 member에 넣고 없으면 새로 만들어서 저장하고 넣는다.
        Member member = memberRepository.findByEmail(email).orElse(
                memberRepository.save(Member.builder()
                        .email(email)
                        .role("ROLE_USER")
                        .build())
        );
        // TokenDTO로 변경해서 저번 주차에 구현한 JWT 형태로 반환
        return MemberResponseDto.LoginResponseDto.builder()
                .id(member.getId())
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

}
