package com.world.alfs.controller.ingredient_allergy;

import com.world.alfs.service.ingredient_allergy.IngredientAllergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredient_allergy")
@Slf4j
public class IngredientAllergyController {

    private final IngredientAllergyService ingredientAllergyService;
}
