package com.world.alfs.domain.ingredient_allergy.repository;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.ingredient_allergy.IngredientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientAllergyRepository extends JpaRepository<IngredientAllergy, Long> {
//    List<Allergy>findByIngredient(List<Long> ingredientList);
    List<Allergy> findAllergiesByIngredientIdIn(List<Long> ingredientList);

}
