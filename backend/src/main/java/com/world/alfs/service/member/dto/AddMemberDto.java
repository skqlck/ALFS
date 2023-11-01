package com.world.alfs.service.member.dto;

import com.world.alfs.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class AddMemberDto {

    private String identifier;

    private String password;

    private String passwordCheck;

    private String name;

    private String email;

    private String phoneNumber;

    private String birth;

    private int point;

    @Builder
    public AddMemberDto(String identifier, String password, String passwordCheck, String name, String email, String phoneNumber, String birth, int point) {
        this.identifier = identifier;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.point = point;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .identifier(identifier)
                .password(password)
                .birth(birth)
                .point(0)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}