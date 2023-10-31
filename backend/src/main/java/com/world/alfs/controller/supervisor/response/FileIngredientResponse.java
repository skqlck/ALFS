package com.world.alfs.controller.supervisor.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FileIngredientResponse {

    private List<String> imgUrls;

    private List<String> ingredients;

    @Builder
    public FileIngredientResponse(List<String> imgUrls, List<String> ingredients) {
        this.imgUrls = imgUrls;
        this.ingredients = ingredients;
    }
}
