package com.world.alfs.service.Address.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressDto {
    private String address_1;
    private String address_2;
    private String alias;

    @Builder
    public AddressDto(String address_1, String address_2, String alias) {
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.alias = alias;
    }

    public String toString() {
        return alias + " : " + address_1 + " " + address_2;
    }
}
