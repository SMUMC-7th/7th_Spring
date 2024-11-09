package com.example.umc7th.domain.member.dto.request;

public class MemberReqDto {

    public record CreateMemberRequestDto(
            String email,
            String password
    ) {
    }

    public record LoginRequestDto(
            String email,
            String password
    ) {
    }

}
