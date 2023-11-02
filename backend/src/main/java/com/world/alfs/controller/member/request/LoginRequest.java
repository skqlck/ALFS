package com.world.alfs.controller.member.request;

import com.world.alfs.service.member.dto.LoginMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String identifier;
    private String password;

    @Builder
    public LoginRequest(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    public LoginMemberDto toDto(){
        return LoginMemberDto.builder()
                .identifier(identifier)
                .password(password)
                .build();
    }
}
