package com.example.umc7th.service.command;

import com.example.umc7th.dto.OAuth2Dto;
import com.example.umc7th.dto.response.MemberResponseDto;

public interface OAuth2Service {
    String getAccessTokenFromKakao(String code);
    OAuth2Dto.KakaoProfile getUserInfo(String accessToken);
    MemberResponseDto.LoginResponseDto login(OAuth2Dto.KakaoProfile userInfo);
}
