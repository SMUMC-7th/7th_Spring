package com.example.umc7th.kakao.service;

import com.example.umc7th.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}
