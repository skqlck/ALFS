package com.world.alfs.controller.allergy.response;

import lombok.Builder;
import lombok.Data;

@Data
public class AllergyResponse {

    private Long id;
    private String allergyName;

    @Builder
    public AllergyResponse(Long id, String allergyName) {
        this.id = id;
        this.allergyName = allergyName;
    }

}
