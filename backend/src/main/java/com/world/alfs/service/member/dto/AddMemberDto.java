package com.world.alfs.service.member.dto;

import com.world.alfs.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class AddMemberDto {
    private Long id;

    private String name;

    private String identifier;

    private String password;

    private int birth;

    private int point;

    private String email;

    private int phoneNumber;

    @Builder
    public AddMemberDto(Long id, String name, String identifier, String password, int birth, int point, String email, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.birth = birth;
        this.point = point;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .identifier(identifier)
                .password(password)
                .birth(birth)
                .point(point)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
