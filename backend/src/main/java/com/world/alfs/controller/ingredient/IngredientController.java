package com.world.alfs.controller.ingredient;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.ingredient.request.AddIngredientRequest;
import com.world.alfs.service.ingredient.IngredientService;
import com.world.alfs.service.ingredient.dto.AddIngredientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient")
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ApiResponse<Long> checkIngredient(@RequestBody List<String> productList) {
        ingredientService.checkIngredient(productList);
        return ApiResponse.ok(1L);
    }

    @PostMapping
    public ApiResponse<Long> addIngredient(@RequestBody AddIngredientRequest request) {
        List<AddIngredientDto> dtoList = request.toDto();
        Long productId = request.getProductId();
        Long resultProductId = ingredientService.addIngredient(productId, dtoList);

        return ApiResponse.ok(resultProductId);
    }

}
