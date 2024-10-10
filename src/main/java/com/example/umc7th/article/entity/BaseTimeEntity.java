package com.example.umc7th.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();
    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();
    @Column(name = "isDeleted", columnDefinition = "BOOLEAN")
    private boolean isDeleted;

    protected BaseTimeEntity() {
        this.isDeleted = false;
    }

    public void softDelete() {
        this.isDeleted = true;
    }

}
