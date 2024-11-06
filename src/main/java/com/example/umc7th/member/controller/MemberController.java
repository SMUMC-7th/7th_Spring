package com.example.umc7th.member.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.member.dto.MemberRequestDTO;
import com.example.umc7th.member.dto.MemberResponseDTO;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.service.command.MemberCommandService;
import com.example.umc7th.member.service.query.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/signUp")
    public CustomResponse<MemberResponseDTO.SignUpResponse> signUp(@RequestBody MemberRequestDTO.SignUpDTO request) {
        MemberResponseDTO.SignUpResponse response = memberCommandService.signUp(request);

        return CustomResponse.onSuccess(MemberResponseDTO.SignUpResponse.from());
    }

}
