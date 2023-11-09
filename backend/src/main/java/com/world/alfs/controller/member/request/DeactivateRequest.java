package com.world.alfs.controller.member.request;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeactivateRequest {

    private Long member_id;
    private String password;

    @Builder
    public DeactivateRequest(Long member_id, String password) {
        this.member_id = member_id;
        this.password = password;
    }
}
