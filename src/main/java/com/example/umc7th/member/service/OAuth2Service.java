package com.example.umc7th.member.service;

import com.example.umc7th.member.dto.LoginResponseDTO;

// OAuth2Service
public interface OAuth2Service {
    LoginResponseDTO login(String code);
}