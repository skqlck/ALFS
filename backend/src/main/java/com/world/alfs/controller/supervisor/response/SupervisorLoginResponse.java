package com.world.alfs.controller.supervisor.response;

import lombok.Builder;
import lombok.Data;

@Data
public class SupervisorLoginResponse {

    private Long supervisorId;

    @Builder
    public SupervisorLoginResponse(Long supervisorId) {
        this.supervisorId = supervisorId;
    }
}
