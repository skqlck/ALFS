package com.world.alfs.service.alternative.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GetAlternativeProductAllDto {

    private List<String> alternativeCategoryList;

    @Builder
    public GetAlternativeProductAllDto(List<String> alternativeCategoryList) {
        this.alternativeCategoryList = alternativeCategoryList;
    }

}
