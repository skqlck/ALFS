package com.world.alfs.controller.member.request;

import com.world.alfs.service.member.dto.LogoutMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LogoutRequest {

    private Long id;

    @Builder
    public LogoutRequest(Long id) {
        this.id = id;
    }

    public LogoutMemberDto toDto(){
        return LogoutMemberDto.builder()
                .id(id)
                .build();
    }
}
