package com.world.alfs.controller.product_ingredient;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.service.product_ingredient.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_ingredient")
@Slf4j
public class ProductIngredientController {


    private final ProductIngredientService productIngredientService;

    @PostMapping()
    public ApiResponse<Long> addProductIngredient(Long productId, Long ingredientId){
        Long id = productIngredientService.addProductIngredient(productId, ingredientId);
        return ApiResponse.ok(id);
    }

}
