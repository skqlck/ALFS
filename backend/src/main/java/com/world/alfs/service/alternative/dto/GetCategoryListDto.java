package com.world.alfs.service.alternative.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class GetCategoryListDto {

    private List<String> idList;

    @Builder
    public GetCategoryListDto(List<String> idList) {
        this.idList = idList;
    }
}
