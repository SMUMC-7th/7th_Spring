package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.enums.SocialType;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.JwtProvider;
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
public class OAuth2ServiceImpl implements OAuth2Service {

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenURI; // Resource Server에 토큰 요청시 사용할 URI

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoURI;// 사용자 정보 가져올 때 사용할 URI

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;// API KEY

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectURI;// 설정한 Redirect uri

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(String provider, String code) {
        if (provider.equalsIgnoreCase(SocialType.KAKAO.name())) {
            return loginWithKakao(code);
        } else {
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

        // orElse() 는 즉시 평가 이기 때문에 값이 있던 없던 내부 로직이 실행 되어 비 효율적 임
        // 아래 처럼 로직을 분리 하면 성능을 높일 수 있음
        member = optional.orElseGet(() -> memberRepository.save(Member.builder()
                .email(email)
                .role("ROLE_USER")
                .build()));


        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

    //인가코드로 토큰 가져오기
    private String getAccessTokenFromKakao(String accessCode) {
        // 인가코드 토큰 가져오기
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        //Content-Type == HTTP 요청 본문의 데이터 형식을 명시
        //application/x-www-form-urlencoded == 데이터가 URL 인코딩 방식으로 전송됨
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("redirect_uri", redirectURI);
        map.add("code", accessCode);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, httpHeaders);

        ResponseEntity<String> response1 = restTemplate.exchange(//이게 http요청을 서버로 보내고 서버로 받은 응답을 반환하는 메서드임
                tokenURI, // URI
                HttpMethod.POST, // Method
                request, // Request 내용
                String.class); // 받을 응답 자료형

        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO = null;

        try {//objectMapper.readValue(변환할 json, 바꿀 클래스.class) -> json 데이터를 java 객체로 변경
            oAuth2TokenDTO = objectMapper.readValue(response1.getBody(), OAuth2DTO.OAuth2TokenDTO.class);
            return oAuth2TokenDTO.getAccess_token();
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    //토큰으로 카카오서버에서 유저정보 가져오기
    private OAuth2DTO.KakaoProfile getProfileFromKakao(String accessToken) {
        // 토큰으로 정보 가져오기
        RestTemplate restTemplate = new RestTemplate();// 요청을 보내기 위한 RestTemplate
        HttpHeaders httpHeaders = new HttpHeaders();//헤더 선언

        //Content-Type == HTTP 요청 본문의 데이터 형식을 명시
        //application/x-www-form-urlencoded == 데이터가 URL 인코딩 방식으로 전송됨

        //Authorization == 인증 관련 정보를 서버에 전달하기 위한 헤더
        httpHeaders.add("Authorization", "Bearer " + accessToken);//Bearer 뒤에 토큰을 추가하면 서버가 요청자의 인증 상태를 확인 가능
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap> request1 = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response2 = restTemplate.exchange(
                userInfoURI,// URI
                HttpMethod.GET, // Method
                request1, // Request 내용
                String.class // 받을 응답 자료형
        );


        ObjectMapper om = new ObjectMapper();

        try {
            return om.readValue(response2.getBody(), OAuth2DTO.KakaoProfile.class);
        } catch (Exception e) {
            throw new MemberException(MemberErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }
}
