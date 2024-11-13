package com.example.umc7th.domain.oauth2.controller;

import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.oauth2.service.OAuth2Service;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<MemberResDto.MemberTokenDto> loginWithKakao(@RequestParam("code") String code) {
        // 서비스 계층에서 로그인 처리
        MemberResDto.MemberTokenDto tokenDto = oAuth2Service.login(code);
        return CustomResponse.onSuccess(tokenDto);
    }
}
