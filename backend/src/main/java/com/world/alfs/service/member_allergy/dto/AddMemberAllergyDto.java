package com.world.alfs.service.member_allergy.dto;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddMemberAllergyDto {
    private Long member_id;
    private Long allergy_id;
    private int isAllergy;

    @Builder
    public AddMemberAllergyDto(Long member_id, Long allergy_id, int isAllergy) {
        this.member_id = member_id;
        this.allergy_id = allergy_id;
        this.isAllergy = isAllergy;
    }

    public MemberAllergy toEntity(Member member, Allergy allergy) {
        return MemberAllergy.builder()
                .member(member)
                .allergy(allergy)
                .build();
    }
}
