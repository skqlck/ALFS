package com.world.alfs.controller.alternative;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.alternative.request.CategoryListRequest;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.service.alternative.AlternativeService;
import com.world.alfs.service.alternative.dto.GetCategoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alternative")
@Slf4j
public class AlternativeController {

    private final AlternativeService alternativeService;

    @PostMapping("/category")
    public ApiResponse<List<CategoryResponse>> getCategory(@RequestBody CategoryListRequest request) {
        GetCategoryListDto dto = request.toDto();
        List<CategoryResponse> categoryResponseList = alternativeService.getCategoryList(dto);

        return ApiResponse.ok(categoryResponseList);
    }

}
