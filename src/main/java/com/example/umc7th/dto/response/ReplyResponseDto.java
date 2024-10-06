package com.example.umc7th.dto.response;


import com.example.umc7th.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ReplyResponseDto {

    public Long articleId;
    public String content;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
