package com.world.alfs.controller.ingredient;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.service.ingredient.IngredientService;
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
}
