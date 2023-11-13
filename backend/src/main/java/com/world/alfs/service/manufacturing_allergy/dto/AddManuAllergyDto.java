package com.world.alfs.service.manufacturing_allergy.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddManuAllergyDto {

    private String allergyName;

}
