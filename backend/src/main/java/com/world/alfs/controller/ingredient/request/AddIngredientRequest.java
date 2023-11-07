package com.world.alfs.controller.ingredient.request;

import com.world.alfs.service.ingredient.dto.AddIngredientDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AddIngredientRequest {

    private Long productId;
    private List<String> ingredientNameList;

    @Builder
    public AddIngredientRequest(Long productId, List<String> ingredientNameList) {
        this.productId = productId;
        this.ingredientNameList = ingredientNameList;
    }

    public List<AddIngredientDto> toDto() {
        List<AddIngredientDto> dtoList = new ArrayList<>();

        for (String name : ingredientNameList) {
            AddIngredientDto dto = AddIngredientDto.builder()
                    .productId(productId)
                    .ingredientName(name)
                    .build();

            dtoList.add(dto);
        }

        return dtoList;
    }

}
