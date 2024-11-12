package com.example.umc7th.domain.oauth.service;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.oauth.dto.OAuth2DTO;

public interface OAuth2Service {

    MemberResponseDTO.MemberTokenDTO kakaoLogin(String code);

    OAuth2DTO.OAuth2TokenDTO getAccessToken(String code);

    OAuth2DTO.KakaoProfile getProfileInfo(OAuth2DTO.OAuth2TokenDTO oAuth2TokenDTO);

    MemberResponseDTO.MemberTokenDTO authenticateOrRegister(OAuth2DTO.KakaoProfile profile);

}