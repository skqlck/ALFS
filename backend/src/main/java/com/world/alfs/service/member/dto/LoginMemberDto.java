package com.world.alfs.service.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginMemberDto {
    private String identifier;
    private String password;

    @Builder
    public LoginMemberDto(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }
}
