package com.world.alfs.controller.alternative.request;

import com.world.alfs.service.alternative.dto.GetCategoryListDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryListRequest {

    private List<String> idList;

    @Builder
    private CategoryListRequest(List<String> idList) {
        this.idList = idList;
    }

    public GetCategoryListDto toDto() {
        return GetCategoryListDto.builder()
                .idList(idList)
                .build();
    }

}
