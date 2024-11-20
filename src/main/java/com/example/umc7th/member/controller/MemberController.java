package com.example.umc7th.member.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc7th.member.dto.SignUpDTO;
import com.example.umc7th.member.service.MemberService;
import com.example.umc7th.member.service.OAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입", description = "이메일과 비밀번호를 입력해서 회원가입하는 API ")
    public CustomResponse<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        memberService.signUp(signUpDTO);
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호를 입력해서 로그인한다.")
    public CustomResponse<?> login(@RequestBody SignUpDTO signUpDTO) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, memberService.login(signUpDTO));
    }

    @GetMapping("/oauth2/callback/kakao")
    // queryParam 형식으로 코드를 받을 예정이니 RequestParam을 설정해줍니다
    // 응답은 저희 서버에 로그인 다 한 뒤에 토큰을 제공할 예정이니 TokenDTO로 설정해줍니다.
    public CustomResponse<?> loginWithKakao(@RequestParam("code") String code) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, oAuth2Service.login(code));

    }
}
