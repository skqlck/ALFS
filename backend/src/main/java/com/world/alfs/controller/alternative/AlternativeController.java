package com.world.alfs.controller.alternative;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.alternative.request.GetAlternativeProductAllRequest;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.service.alternative.AlternativeService;
import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
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

    @GetMapping("/category/list/{memberId}")
    public ApiResponse<List<CategoryResponse>> getCategory(@PathVariable Long memberId) {
        List<CategoryResponse> categoryResponseList = alternativeService.getCategoryList(memberId);

        return ApiResponse.ok(categoryResponseList);
    }

    @GetMapping("/category/{name}")
    public ApiResponse<List<GetProductListResponse>> getAlternativeProduct(@PathVariable String name) {
        List<GetProductListResponse> productListResponse = alternativeService.getAlternativeProduct(name);
        return ApiResponse.ok(productListResponse);
    }

    @PostMapping("/all")
    public ApiResponse<List<GetProductListResponse>> getAlternativeProductAll(@RequestBody GetAlternativeProductAllRequest request) {
        GetAlternativeProductAllDto dto = request.toDto();
        List<GetProductListResponse> productListResponse = alternativeService.getAlternativeProductAll(dto);
        return ApiResponse.ok(productListResponse);
    }

}
