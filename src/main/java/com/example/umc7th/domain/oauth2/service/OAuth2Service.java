package com.example.umc7th.domain.oauth2.service;

import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.oauth2.dto.OAuth2Dto;

public interface OAuth2Service {
    MemberResDto.MemberTokenDto login(String code);
    OAuth2Dto.KakaoProfile getUserInfo(String accessToken);
    OAuth2Dto.OAuth2TokenDto getAccessToken(String code);
}
