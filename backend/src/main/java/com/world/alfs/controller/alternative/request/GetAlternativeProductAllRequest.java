package com.world.alfs.controller.alternative.request;

import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetAlternativeProductAllRequest {

    private List<String> alternativeCategoryList;

    @Builder
    public GetAlternativeProductAllRequest(List<String> alternativeCategoryList) {
        this.alternativeCategoryList = alternativeCategoryList;
    }

    public GetAlternativeProductAllDto toDto() {
        return GetAlternativeProductAllDto.builder()
                .alternativeCategoryList(alternativeCategoryList)
                .build();
    }

}
