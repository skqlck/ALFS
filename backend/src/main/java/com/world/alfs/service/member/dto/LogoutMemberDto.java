package com.world.alfs.service.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LogoutMemberDto {
    private Long id;

    @Builder
    public LogoutMemberDto(Long id) {
        this.id = id;
    }
}
