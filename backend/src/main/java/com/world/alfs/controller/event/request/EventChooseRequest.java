package com.world.alfs.controller.event.request;

import com.world.alfs.service.event.dto.EventDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventChooseRequest {

    private Long id;
    private int choose_case;

    @Builder
    public EventChooseRequest(Long id, int choose_case) {
        this.id = id;
        this.choose_case = choose_case;
    }

    public EventDto toDto() {
        return EventDto.builder()
                .id(id)
                .choose_case(choose_case)
                .build();
    }
}
