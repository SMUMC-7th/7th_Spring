package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;

public interface MemberCommandService {

    MemberResponseDTO.CreateMemberResultDTO createMember(MemberRequestDTO.CreateMemberDTO dto);

    MemberResponseDTO.LoginResponseDTO login(MemberRequestDTO.LoginRequestDTO dto);

    void updateMember(Long memberId, MemberRequestDTO.UpdateMemberDTO dto);

    void deleteMember(Long memberId);

}
