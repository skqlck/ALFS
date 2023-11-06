package com.world.alfs.service.ingredient;

import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public List<Long> checkIngredient(List<String> productList) {
        List<Long>list = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (ingredientRepository.findByName(productList.get(i)).isEmpty()) {
                Ingredient ingredient = Ingredient.builder()
                        .name(productList.get(i))
                        .build();
                ingredientRepository.save(ingredient);
            }
            Optional<Ingredient> savedIngredient = ingredientRepository.findByName(productList.get(i));
            list.add(savedIngredient.get().getId());
        }
        return list;
    }
}
