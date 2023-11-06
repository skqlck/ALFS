package com.world.alfs.service.manufacturing_allergy.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetManuAllergyDto {

    private Long memberId;
    private Long productId;

    @Builder
    public GetManuAllergyDto(Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }
    
}
