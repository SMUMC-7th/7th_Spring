package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberReqDTO;
import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.entity.Member;

public interface MemberCommandService {
    MemberResDTO.MemberPreviewDTO signup(MemberReqDTO.SignupReqDTO dto);
    MemberResDTO.LoginResDTO login(MemberReqDTO.LoginReqDTO dto);
}
