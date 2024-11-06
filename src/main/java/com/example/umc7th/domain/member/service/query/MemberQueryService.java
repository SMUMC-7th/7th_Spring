package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;

public interface MemberQueryService {

    MemberResponseDTO.MemberViewDTO getMember(Long id);

}
