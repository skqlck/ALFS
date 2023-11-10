package com.world.alfs.controller.member.response;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetMemberResponse {
    private Long member_id;
    private String identifier;
    private String email;
    private String name;
    private String birth;
    private String phoneNumber;

    @Builder
    public GetMemberResponse(Long member_id, String identifier, String email, String name, String birth, String phoneNumber) {
        this.member_id = member_id;
        this.identifier = identifier;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }
}
