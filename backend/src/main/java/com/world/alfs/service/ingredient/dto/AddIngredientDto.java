package com.world.alfs.service.ingredient.dto;

import com.world.alfs.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddIngredientDto {

    private Long productId;
    private String ingredientName;

    @Builder
    public AddIngredientDto(Long productId, String ingredientName) {
        this.productId = productId;
        this.ingredientName = ingredientName;
    }

    public Ingredient toEntity() {
        return Ingredient.builder()
                .name(ingredientName)
                .build();
    }

}
