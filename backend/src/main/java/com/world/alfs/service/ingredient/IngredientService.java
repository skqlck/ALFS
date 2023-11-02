package com.world.alfs.service.ingredient;

import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public void checkIngredient(Long productId, List<String> productList) {
        for (int i = 0; i < productList.size(); i++) {
            if (ingredientRepository.findByName(productList.get(i)) == null) {
                Ingredient ingredient = Ingredient.builder()
                        .name(productList.get(i))
                        .build();
                ingredientRepository.save(ingredient);
            }
            Optional<Ingredient> savedId = ingredientRepository.findByName(productList.get(i));
            //redirect 로 product_id랑 savedId를 통해서 product_ingredient 상품원재료명 등록
        }
    }
}
