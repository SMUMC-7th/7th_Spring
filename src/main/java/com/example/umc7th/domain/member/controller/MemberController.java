package com.example.umc7th.domain.member.controller;

import com.example.umc7th.domain.member.dto.MemberReqDTO;
import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.service.command.MemberCommandService;
import com.example.umc7th.domain.member.service.query.MemberQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "멤버 관련 API")
public class MemberController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    @Operation(method = "POST", summary = "회원가입 API", description = "회원 가입 API입니다.")
    public CustomResponse<MemberResDTO.MemberPreviewDTO> signup(
            @RequestBody MemberReqDTO.SignupReqDTO dto
            ){
        MemberResDTO.MemberPreviewDTO result = memberCommandService.signup(dto);
        return CustomResponse.onSuccess(HttpStatus.CREATED, result);
    }

    @PostMapping("/login")
    @Operation(method = "POST", summary = "로그인 API", description = "로그인 API입니다.")
    public CustomResponse<MemberResDTO.LoginResDTO> signUp(@RequestBody MemberReqDTO.LoginReqDTO dto) {
        MemberResDTO.LoginResDTO result = memberCommandService.login(dto);
        return CustomResponse.onSuccess(HttpStatus.OK, result);
    }
}
