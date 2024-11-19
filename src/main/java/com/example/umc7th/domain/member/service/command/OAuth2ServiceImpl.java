package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.enums.SocialType;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service{

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

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String provider, String code) {
        if (provider.equalsIgnoreCase(SocialType.KAKAO.name())) {
            return loginWithKakao(code);
        }
        else {
            throw new MemberException(MemberErrorCode.UNSUPPORTED_OAUTH_TYPE);
        }
    }

    private MemberResponseDTO.MemberTokenDTO loginWithKakao(String code) {
        String token = getAccessTokenFromKakao(code);
        OAuth2DTO.KakaoProfile profile = getProfileFromKakao(token);
        String email = profile.getKakao_account().getEmail();
        return loginOrSignup(SocialType.KAKAO, email);
    }

    private MemberResponseDTO.MemberTokenDTO loginOrSignup(SocialType socialType, String email) {
        // SocialType을 Member에 provider라는 필드로 추가해서 저장해도 좋음
        Member member;
        Optional<Member> optional = memberRepository.findByEmail(email);
        member = optional.orElseGet(() -> memberRepository.save(Member.builder()
                .email(email)
                .role("ROLE_USER")
                .build()));


        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

    private String getAccessTokenFromKakao(String accessCode) {
        // 인가코드 토큰 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", accessCode);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> response1 = restTemplate.exchange(
                tokenURI,
                HttpMethod.POST,
                request,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = null;

        try {
            oAuth2TokenDTO = objectMapper.readValue(response1.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
            return oAuth2TokenDTO.getAccess_token();
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }
    private OAuth2DTO.KakaoProfile getProfileFromKakao(String accessToken) {
        // 토큰으로 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap> request1 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response2 = restTemplate.exchange(
                userInfoURI,
                HttpMethod.GET,
                request1,
                String.class
        );

        ObjectMapper om = new ObjectMapper();

        try {
            return om.readValue(response2.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch(Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }
}
