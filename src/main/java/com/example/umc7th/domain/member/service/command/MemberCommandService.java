package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberDto;
import com.example.umc7th.domain.member.entity.Member;

public interface MemberCommandService {
    Member signUp(MemberDto memberDto);
}
