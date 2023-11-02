package com.world.alfs.controller.member.request;

import com.world.alfs.service.address.dto.AddressDto;
import com.world.alfs.service.member.dto.AddMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private AddMemberDto member;
    private AddressDto address;

    @Builder
    public SignUpRequest(AddMemberDto member, AddressDto address) {
        this.member = member;
        this.address = address;
    }
}
