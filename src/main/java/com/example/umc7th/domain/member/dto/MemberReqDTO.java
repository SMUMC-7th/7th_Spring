package com.example.umc7th.domain.member.dto;

public class MemberReqDTO {
    public record SignupReqDTO(
            String email,
            String password){}

    public record LoginReqDTO(
            String email,
            String password){}
}
