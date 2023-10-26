package com.world.alfs.controller.product;

import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Long addProduct(@RequestBody AddProductRequest request){
        AddProductDto dto = request.toDto();
        return productService.addProduct(dto);

    }


}
