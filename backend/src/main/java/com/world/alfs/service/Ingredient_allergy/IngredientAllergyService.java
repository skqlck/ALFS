package com.world.alfs.service.Ingredient_allergy;

import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient_allergy.IngredientAllergy;
import com.world.alfs.domain.ingredient_allergy.repository.IngredientAllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class IngredientAllergyService {
    private final IngredientAllergyRepository ingredientAllergyRepository;

    public List<Long> getAllAllergyId(List<Long> list, Ingredient ingredient) {
//        List<Long> response = new ArrayList<>();
//        for (int i = 0; i < ingredientList.size(); i++) {
//            List<IngredientAllergy> ingredientAllergyList = ingredientAllergyRepository.findAllByIngredientIn(ingredientList);
//            for (int j = 0; j < ingredientAllergyList.size(); j++) {
//                Long input = ingredientAllergyList.get(j).getAllergy().getId();
//                response.add(input);
//            }
//        }
        List<IngredientAllergy> ingredientAllergyList = ingredientAllergyRepository.findAllByIngredient(ingredient);
        for (int j = 0; j < ingredientAllergyList.size(); j++) {
            Long input = ingredientAllergyList.get(j).getAllergy().getId();
            list.add(input);
        }

        return list;
    }
}
