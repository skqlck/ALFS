package com.world.alfs.controller.product;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public Long addProduct(@RequestBody AddProductRequest request){
        AddProductDto dto = request.toDto();
        return productService.addProduct(dto);
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<ProductResponse>> getProduct(@PathVariable Long id){
        Optional<ProductResponse> savedProduct = productService.getProduct(id);
        return ApiResponse.ok(savedProduct);
    }


    @GetMapping("/all")
    public ApiResponse<List<GetProductListResponse>>getAllProduct(){
        List<GetProductListResponse> product_list = productService.getAllProduct();
        return ApiResponse.ok(product_list);
    }

    @PatchMapping()
    public ApiResponse<Long>setProduct(@RequestBody AddProductRequest request){
        AddProductDto dto = request.toDto();
        Long savedId = productService.setProduct(dto);
        return ApiResponse.ok(savedId);

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Long>deleteProduct(@PathVariable Long id){
        Long savedId = productService.deleteProduct(id);
        return ApiResponse.ok(savedId);
    }





}
