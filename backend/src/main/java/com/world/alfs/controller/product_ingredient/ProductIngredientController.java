package com.world.alfs.controller.product_ingredient;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.service.ingredient.IngredientService;
import com.world.alfs.service.product_ingredient.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product_ingredient")
@Slf4j
public class ProductIngredientController {

    private final ProductIngredientService productIngredientService;
    private final IngredientService ingredientService;

    @PostMapping("/{productId}")
    public ApiResponse<Long> addProductIngredient(@PathVariable Long productId, @RequestBody List<String> list) {
        List<Long> ingredientList = ingredientService.checkIngredient(list);
        for (Long id : ingredientList) {
            productIngredientService.addProductIngredient(productId, id);
        }
        return ApiResponse.ok(productId);
    }

}
