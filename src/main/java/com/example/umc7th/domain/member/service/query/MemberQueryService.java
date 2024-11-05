package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.entity.Member;

public interface MemberQueryService {
    Member login(MemberRequestDTO.LoginDTO dto);
}
