package com.example.umc7th.global.oauth.controller;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.oauth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        // 로직 구현 필요
        System.out.println(code);
        return null;
        // 서비스 생성 이후
        // return CustomResponse.onSuccess(oAuth2Service.login(code));

    }

}