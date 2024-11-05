package com.example.umc7th.domain.member.dto;

import lombok.*;

public class MemberResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberInfoDTO {
        private String email;
    }

}
