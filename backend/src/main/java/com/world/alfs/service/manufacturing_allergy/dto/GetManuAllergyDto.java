package com.world.alfs.service.manufacturing_allergy.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetManuAllergyDto {

    private Long memberId;
    private Long productId;
    
}
