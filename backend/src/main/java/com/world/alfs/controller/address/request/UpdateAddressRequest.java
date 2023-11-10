package com.world.alfs.controller.address.request;

import com.world.alfs.service.address.dto.AddressDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateAddressRequest {
    private Long member_id;
    private Long address_id;
    private AddressDto address;

    @Builder
    public UpdateAddressRequest(Long member_id, Long address_id, AddressDto address) {
        this.member_id = member_id;
        this.address_id = address_id;
        this.address = address;
    }
}
