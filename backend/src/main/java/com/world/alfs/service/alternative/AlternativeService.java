package com.world.alfs.service.alternative;

import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.domain.alternative.Alternative;
import com.world.alfs.domain.alternative.repository.AlternativeRepository;
import com.world.alfs.service.alternative.dto.GetCategoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;

    // 대체 식품 카테고리 리스트
    public List<CategoryResponse> getCategoryList(GetCategoryListDto dto) {
        List<String> list = alternativeRepository.findByParentIdIn(dto.getIdList()).stream()
                .map(Alternative::getAlternativeName)
                .distinct()
                .collect(Collectors.toList());

        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (String s : list) {
            CategoryResponse response = CategoryResponse.builder()
                    .alternativeName(s)
                    .build();

            categoryResponseList.add(response);
        }

        return categoryResponseList;
    }

}
