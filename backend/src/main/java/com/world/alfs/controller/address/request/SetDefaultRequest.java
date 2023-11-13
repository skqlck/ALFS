package com.world.alfs.controller.address.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetDefaultRequest {
    private Long member_id;
    private Long address_id;

    @Builder
    public SetDefaultRequest(Long id, Long address_id) {
        this.member_id = id;
        this.address_id = address_id;
    }
}
