package com.world.alfs.controller.event.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventResponse {
    private int case1_rate;
    private int case2_rate;

    @Builder
    public EventResponse(int case1_rate, int case2_rate) {
        this.case1_rate = case1_rate;
        this.case2_rate = case2_rate;
    }
}
