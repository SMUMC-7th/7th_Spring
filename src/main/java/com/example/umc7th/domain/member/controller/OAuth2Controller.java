package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.service.command.OAuth2Service;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@Tag(name = "OAuth2 관련 API")
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;

    @GetMapping("/oauth2/callback/kakao")
    @Operation(method = "GET", summary = "카카오 소셜 로그인 API", description = "카카오 소셜 로그인 API입니다.")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<MemberResDTO.MemberTokenDTO> loginWithKakao(@RequestParam("code") String code) {
        // 로직 구현 필요
        MemberResDTO.MemberTokenDTO result = oAuth2Service.login("kakao",code);

        // 서비스 생성 이후
        return CustomResponse.onSuccess(HttpStatus.OK, result);

    }
}
