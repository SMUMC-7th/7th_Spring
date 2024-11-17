package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResDTO;

public interface OAuth2Service {
    MemberResDTO.MemberTokenDTO login(String provider, String code);
}
