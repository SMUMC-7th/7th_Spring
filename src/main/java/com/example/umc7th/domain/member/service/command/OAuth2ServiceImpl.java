package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @Override
    public OAuth2DTO.KakaoProfile getUserInfo(String accessToken){
        String response = WebClient.create()
                .get()
                .uri(userInfoURI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return new ObjectMapper().readValue(response, OAuth2DTO.KakaoProfile.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    @Override
    public OAuth2DTO.OAuth2TokenDTO getAccessToken(String code){
        String response = WebClient.create()
                .post()
                .uri(tokenURI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(createTokenRequestBody(code))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            return new ObjectMapper().readValue(response, OAuth2DTO.OAuth2TokenDTO.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    //accessToken 요청을 위한 파라미터 설정
    private MultiValueMap<String, String> createTokenRequestBody(String code) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", code);
        return map;
    }

    @Override
    public MemberResDTO.MemberTokenDTO login(String code) {
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = getAccessToken(code);
        OAuth2DTO.KakaoProfile profile = getUserInfo(oAuth2TokenDTO.access_token());

        // 회원가입이 되었으면 사용자 로그인 안되어있으면 회원가입 후 로그인
        String email = profile.kakao_account().email(); // Kakao에서의 Id를 가지고 Email로 변경

        // email을 찾고 있으면 member에 넣고 없으면 새로 만들어서 저장하고 넣는다.
        Member member = memberRepository.findByEmail(email).orElse(
                memberRepository.save(Member.builder()
                        .email(email)
                        .role("ROLE_USER")
                        .build())
        );

        // TokenDTO로 변경해서 저번 주차에 구현한 JWT 형태로 반환
        return MemberResDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}
