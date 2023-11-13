package com.world.alfs.controller.alternative.response;

import lombok.Builder;
import lombok.Data;

@Data
public class CategoryResponse {

    private String alternativeName;

    @Builder
    public CategoryResponse(String alternativeName) {
        this.alternativeName = alternativeName;
    }

}
