package com.world.alfs.controller.manufacturing_allergy.request;

import com.world.alfs.service.manufacturing_allergy.dto.AddManuAllergyDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddManuAllergyRequest {

    private Long productId;
    private List<String> allergyNameList;

    public List<AddManuAllergyDto> toDto() {
        List<AddManuAllergyDto> dtoList = new ArrayList<>();

        for(String name : allergyNameList) {
            AddManuAllergyDto dto = AddManuAllergyDto.builder()
                    .allergyName(name)
                    .build();
            dtoList.add(dto);
        }

        return dtoList;
    }

}
