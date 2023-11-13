package com.world.alfs.service.speical.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeleteSpecialDto {

    private Long supervisorId;

    @Builder
    public DeleteSpecialDto(Long supervisorId){
        this.supervisorId = supervisorId;
    }

}
