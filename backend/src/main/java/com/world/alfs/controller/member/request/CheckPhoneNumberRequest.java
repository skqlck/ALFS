package com.world.alfs.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckPhoneNumberRequest {
    private String phoneNumber;

    @Builder
    public CheckPhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
