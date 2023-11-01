package com.world.alfs.controller.supervisor.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SupervisorLoginResponse {

    private String identifier;

    @Builder
    public SupervisorLoginResponse(String identifier) {
        this.identifier = identifier;
    }
}
