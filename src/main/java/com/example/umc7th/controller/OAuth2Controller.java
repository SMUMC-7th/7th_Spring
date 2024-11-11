package com.example.umc7th.controller;

import com.example.umc7th.dto.OAuth2Dto;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import com.example.umc7th.service.command.OAuth2Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@Slf4j
@Tag(name = "OAuth2 API", description = "OAuth2 관련 API")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    public CustomResponse<MemberResponseDto.LoginResponseDto> loginWithKakao(@RequestParam("code") String code) {

        String accessToken = oAuth2Service.getAccessTokenFromKakao(code);
        log.info("code로 AT추출 완료 ");
        OAuth2Dto.KakaoProfile userInfo = oAuth2Service.getUserInfo(accessToken);
        log.info("AT로 userInfo 추출 완료 ");
        MemberResponseDto.LoginResponseDto result = oAuth2Service.login(userInfo);
        log.info("로그인 완료 ");

        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }
}
