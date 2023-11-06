package com.world.alfs.service.Ingredient_allergy;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.ingredient_allergy.repository.IngredientAllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class IngredientAllergyService {
    private final IngredientAllergyRepository ingredientAllergyRepository;

    public List<Long> getAllAllergyId(List<Long> ingredientList) {
        List<Long> response = new ArrayList<>();
        List<Allergy> list = ingredientAllergyRepository.findAllergiesByIngredientIdIn(ingredientList);
        for(int i=0; i<list.size(); i++){
            response.add(list.get(i).getId());
        }
        return response;
    }
}
