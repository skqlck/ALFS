package com.world.alfs.controller.address.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAddressResponse {
    private Long id;
    private String address_1;
    private String address_2;
    private String alias;
    private Boolean status;

    @Builder
    public GetAddressResponse(Long id, String address_1, String address_2, String alias, Boolean status) {
        this.id = id;
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.alias = alias;
        this.status = status;
    }
}
