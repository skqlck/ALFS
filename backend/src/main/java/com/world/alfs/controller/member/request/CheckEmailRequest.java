package com.world.alfs.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckEmailRequest {
    private String email;

    @Builder
    public CheckEmailRequest(String email) {
        this.email = email;
    }
}
