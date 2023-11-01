package com.world.alfs.controller.member.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckIdentifierRequest {
    private String identifier;

    @Builder
    public CheckIdentifierRequest(String identifier) {
        this.identifier = identifier;
    }
}
