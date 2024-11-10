package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.OAuth2DTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.enums.SocialType;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OAuth2LibraryService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2DTO.OAuth2LoginDTO loginDTO;
        SocialType socialType;

        if (userRequest.getClientRegistration().getClientName().equals("kakao")) {
            loginDTO = saveOrUpdateWithKakao(oAuth2User);
            socialType = SocialType.KAKAO;
        }
        else {
            return oAuth2User;
        }

        Optional<Member> optional = memberRepository.findByEmailAndSocialType(loginDTO.getEmail(), socialType);
        Member member;
        if (optional.isPresent()) {
            member = optional.get();
            member.update(loginDTO.getProfileImage(), loginDTO.getThumbnailImage());
        }
        else {
            member = Member.builder()
                    .email(loginDTO.getEmail())
                    .profileImage(loginDTO.getProfileImage())
                    .thumbnailImage(loginDTO.getThumbnailImage())
                    .socialType(SocialType.KAKAO)
                    .build();
        }
        memberRepository.save(member);

        return oAuth2User;
    }

    private OAuth2DTO.OAuth2LoginDTO saveOrUpdateWithKakao(OAuth2User oAuth2User) {
        LinkedHashMap<String, Object> properties = oAuth2User.getAttribute("properties");
        LinkedHashMap<String, Object> kakaoAccount= oAuth2User.getAttribute("kakao_account");

        String profileImage = String.valueOf(properties.get("profile_image"));
        String thumbnailImage = String.valueOf(properties.get("thumbnail_image"));
        String email = String.valueOf(kakaoAccount.get("email"));
        Long id = oAuth2User.getAttribute("id");

        return OAuth2DTO.OAuth2LoginDTO.builder()
                .email(email)
                .profileImage(profileImage)
                .thumbnailImage(thumbnailImage)
                .id(id)
                .build();
    }

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        if (userRequest.getClientRegistration().getClientName().equals("kakao")) {
//            saveOrUpdateWithKakao(oAuth2User);
//        }
//        return oAuth2User;
//    }
//
//    private void saveOrUpdateWithKakao(OAuth2User oAuth2User) {
//        LinkedHashMap<String, Object> properties = oAuth2User.getAttribute("properties");
//        LinkedHashMap<String, Object> kakaoAccount= oAuth2User.getAttribute("kakao_account");
//
//        String profileImage = String.valueOf(properties.get("profile_image"));
//        String thumbnailImage = String.valueOf(properties.get("thumbnail_image"));
//        String email = String.valueOf(kakaoAccount.get("email"));
//        Long id = oAuth2User.getAttribute("id");
//
//        Optional<Member> optional = memberRepository.findByEmail(email);
//        Member member;
//        if (optional.isPresent()) {
//            member = optional.get();
//            member.update(profileImage, thumbnailImage);
//        }
//        else {
//            member = Member.builder()
//                    .email(email)
//                    .profileImage(profileImage)
//                    .thumbnailImage(thumbnailImage)
//                    .socialType(SocialType.KAKAO)
//                    .build();
//        }
//        memberRepository.save(member);
//    }
}
