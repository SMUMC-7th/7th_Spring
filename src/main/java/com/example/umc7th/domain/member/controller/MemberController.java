package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.domain.member.service.command.OAuth2Service;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final OAuth2Service oAuth2Service;

    @PostMapping("/login")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> login(@RequestBody MemberRequestDTO.MemberLoginDTO dto) {
        return CustomResponse.onSuccess(memberCommandService.login(dto));
    }

    @PostMapping("/signup")
    public CustomResponse<MemberResponseDTO.MemberTokenDTO> signup(@RequestBody MemberRequestDTO.MemberSignUpDTO dto) {
        return CustomResponse.onSuccess(memberCommandService.signUp(dto));
    }

//    @GetMapping("/oauth2/callback/{provider}")
//    public CustomResponse<MemberResponseDTO.MemberTokenDTO> loginWithKakao(@PathVariable String provider,
//                                                                           @RequestParam("code") String code) {
//        return CustomResponse.onSuccess(oAuth2Service.login(provider, code));
//    }
}
