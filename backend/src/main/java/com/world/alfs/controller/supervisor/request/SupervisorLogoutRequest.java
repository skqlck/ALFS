package com.world.alfs.controller.supervisor.request;

import com.world.alfs.service.supervisor.dto.LogoutSupervisorDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupervisorLogoutRequest {

    private Long id;

    @Builder
    public SupervisorLogoutRequest(Long id) {
        this.id = id;
    }

    public LogoutSupervisorDto toDto(){
        return LogoutSupervisorDto.builder()
                .id(this.id)
                .build();
    }
}
