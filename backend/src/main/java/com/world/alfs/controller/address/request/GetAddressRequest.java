package com.world.alfs.controller.address.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAddressRequest {
    private Long id;

    @Builder
    public GetAddressRequest(Long id) {
        this.id = id;
    }
}
