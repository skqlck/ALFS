package com.world.alfs.service.speical.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class SetSpecialDto {

    private int status;

    private LocalDateTime start;

    private LocalDateTime end;

    private int count;

    private int salePrice;

    private Long supervisorId;


    @Builder
    public SetSpecialDto(int status, LocalDateTime start, LocalDateTime end, int count, int salePrice, Long supervisorId){
        this.status = status;
        this.start = start;
        this.end = end;
        this.count = count;
        this.salePrice = salePrice;
        this.supervisorId = supervisorId;
    }

}
