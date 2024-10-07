package com.example.umc7th.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
// 생성 및 수정 시간 감지
@EntityListeners(AuditingEntityListener.class)
// 다른 Entity에서 상속받아 사용할 수 있도록 설정
@MappedSuperclass
public class BaseEntity {

    // 해당 Column에 수정시간 자동 mapping
    @CreatedDate
    private LocalDateTime createdAt;

    // 해당 Column에 수정시간 자동 mapping
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
