package com.world.alfs.controller.alternative;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.alternative.request.GetAlternativeProductAllRequest;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.controller.product.ProductController;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.service.alternative.AlternativeService;
import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
import com.world.alfs.service.product.ProductService;
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
    private final ProductService productService;

    private final ProductController productController;

    @GetMapping("/category/list/{memberId}")
    public ApiResponse<List<CategoryResponse>> getCategory(@PathVariable Long memberId) {
        List<CategoryResponse> categoryResponseList = alternativeService.getCategoryList(memberId);

        return ApiResponse.ok(categoryResponseList);
    }

    @GetMapping("/category/{name}/{memberId}")
    public ApiResponse<List<GetProductListResponse>> getAlternativeProduct(@PathVariable String name, @PathVariable Long memberId) {
        List<Product> productList = alternativeService.getAlternativeProduct(name);
        List<GetProductListResponse> productListResponse = productService.getAllProduct(productList);
        productController.allergy_filter(productList, productListResponse, memberId);
        return ApiResponse.ok(productListResponse);
    }

    @PostMapping("/all/{memberId}")
    public ApiResponse<List<GetProductListResponse>> getAlternativeProductAll(@RequestBody GetAlternativeProductAllRequest request, @PathVariable Long memberId) {
        GetAlternativeProductAllDto dto = request.toDto();
        List<Product> productList = alternativeService.getAlternativeProductAll(dto);
        List<GetProductListResponse> responseList = productService.getAllProduct(productList);
        productController.allergy_filter(productList, responseList, memberId);
        return ApiResponse.ok(responseList);
    }

}
