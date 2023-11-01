package com.world.alfs.controller.alternative;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.alternative.request.CategoryListRequest;
import com.world.alfs.controller.alternative.request.GetAlternativeProductAllRequest;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.service.alternative.AlternativeService;
import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
import com.world.alfs.service.alternative.dto.GetCategoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/category/{name}")
    public ApiResponse<List<ProductResponse>> getAlternativeProduct(@PathVariable String name) {
        List<ProductResponse> productResponseList = alternativeService.getAlternativeProduct(name);
        return ApiResponse.ok(productResponseList);
    }

    @PostMapping("/all")
    public ApiResponse<List<ProductResponse>> getAlternativeProductAll(@RequestBody GetAlternativeProductAllRequest request) {
        GetAlternativeProductAllDto dto = request.toDto();
        List<ProductResponse> productResponseList = alternativeService.getAlternativeProductAll(dto);
        return ApiResponse.ok(productResponseList);
    }

}
