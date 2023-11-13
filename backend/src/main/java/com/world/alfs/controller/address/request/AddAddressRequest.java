package com.world.alfs.controller.address.request;

import com.world.alfs.service.address.dto.AddressDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAddressRequest {
    private Long member_id;
    private AddressDto address;

    @Builder
    public AddAddressRequest(Long member_id, AddressDto addressDto) {
        this.member_id = member_id;
        this.address = addressDto;
    }
}
