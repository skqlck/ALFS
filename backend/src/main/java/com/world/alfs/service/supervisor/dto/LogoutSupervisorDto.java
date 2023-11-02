package com.world.alfs.service.supervisor.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LogoutSupervisorDto {

    private Long id;

    @Builder
    public LogoutSupervisorDto(Long id) {
        this.id = id;
    }


}
