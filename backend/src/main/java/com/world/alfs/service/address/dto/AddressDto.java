package com.world.alfs.service.address.dto;

import com.world.alfs.domain.address.Address;
import com.world.alfs.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddressDto {
    private Member member;
    private String address_1;
    private String address_2;
    private String alias;
    private Boolean status;

    @Builder
    public AddressDto(String address_1, String address_2, String alias) {
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.alias = alias;
    }

    public Address toEntity(Boolean status){
        return Address.builder()
                .address_1(address_1)
                .address_2(address_2)
                .alias(alias)
                .status(status)
                .member(member)
                .build();
    }

    public void setMember(Member member){
        this.member = member;
    }

    public String toString() {
        return alias + " : " + address_1 + " " + address_2;
    }
}
