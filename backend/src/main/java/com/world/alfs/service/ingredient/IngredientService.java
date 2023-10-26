package com.world.alfs.service.ingredient;

import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;
}
