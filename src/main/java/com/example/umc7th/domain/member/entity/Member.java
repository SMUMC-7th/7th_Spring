package com.example.umc7th.domain.member.entity;

import com.example.umc7th.domain.member.enums.SocialType;
import com.example.umc7th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private SocialType socialType;

    public void update(String profileImage, String thumbnailImage) {
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
    }
}
