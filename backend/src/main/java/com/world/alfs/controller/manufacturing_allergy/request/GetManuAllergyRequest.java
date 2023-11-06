package com.world.alfs.controller.manufacturing_allergy.request;

import com.world.alfs.service.manufacturing_allergy.dto.GetManuAllergyDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetManuAllergyRequest {

    private Long memberId;
    private Long productId;

    @Builder
    public GetManuAllergyRequest(Long memberId, Long productId) {
        this.memberId = memberId;
        this.productId = productId;
    }

    public GetManuAllergyDto toDto() {
        return GetManuAllergyDto.builder()
                .memberId(memberId)
                .productId(productId)
                .build();
    }

}
