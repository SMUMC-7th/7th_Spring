package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.MemberResponseDto;

public interface MemberQueryService {
    MemberResponseDto.MemberPreviewDto getMember(Long id);

}
