package com.world.alfs.service.event.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EventDto {

    private Long id;
    private int choose_case;

    @Builder
    public EventDto(Long id, int choose_case) {
        this.id = id;
        this.choose_case = choose_case;
    }
}
