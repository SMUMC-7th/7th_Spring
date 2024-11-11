package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberDto;

public interface MemberQueryService {
    boolean login(MemberDto memberDto);
}
