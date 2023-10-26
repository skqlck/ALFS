package com.world.alfs.domain.ingredient_allergy.repository;

import com.world.alfs.domain.ingredient_allergy.IngredientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientAllergyRepository extends JpaRepository<IngredientAllergy, Long> {
}
