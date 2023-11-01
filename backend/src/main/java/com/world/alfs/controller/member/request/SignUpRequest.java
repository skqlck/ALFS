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
    private List<String> allergy;
    private List<String> hate;
    private AddressDto address;

    @Builder
    public SignUpRequest(AddMemberDto member, List<String> allergy, List<String> hate, AddressDto address) {
        this.member = member;
        this.allergy = allergy;
        this.hate = hate;
        this.address = address;
    }
}
