package com.example.umc7th.controller;

import com.example.umc7th.dto.request.MemberRequestDto;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import com.example.umc7th.service.command.MemberCommandService;
import com.example.umc7th.service.query.MemberQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
@Tag(name = "맴버 API", description = "맴버 관련 API")
public class MemberController {
    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/register")
    public CustomResponse<MemberResponseDto.MemberPreviewDto> createMember(@RequestBody MemberRequestDto.CreateMemberRequestDto dto){
        MemberResponseDto.MemberPreviewDto result = memberCommandService.createMember(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED_201, result);
    }

    @PostMapping("/login")
    public CustomResponse<MemberResponseDto.LoginResponseDto> login(@RequestBody MemberRequestDto.LoginRequestDto dto){
        MemberResponseDto.LoginResponseDto result = memberCommandService.login(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @GetMapping
    public CustomResponse<MemberResponseDto.MemberPreviewDto> getMember(@RequestParam Long id){
        MemberResponseDto.MemberPreviewDto result = memberQueryService.getMember(id);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @PutMapping
    public CustomResponse<MemberResponseDto.MemberPreviewDto> updateMember(@RequestBody MemberRequestDto.UpdateMemberRequestDto dto){
        MemberResponseDto.MemberPreviewDto result = memberCommandService.updateMember(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @DeleteMapping
    public CustomResponse<String> deleteMember(@RequestParam Long id){
        memberCommandService.deleteMember(id);
        return CustomResponse.onSuccess(GeneralSuccessCode.NO_CONTENT_204, "맴버 삭제가 완료되었습니다.");
    }
}
