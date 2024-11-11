package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.dto.OAuth2DTO;

public interface OAuth2Service {
    MemberResDTO.MemberTokenDTO login(String code);
    OAuth2DTO.KakaoProfile getUserInfo(String accessToken);
    OAuth2DTO.OAuth2TokenDTO getAccessToken(String code);

}
