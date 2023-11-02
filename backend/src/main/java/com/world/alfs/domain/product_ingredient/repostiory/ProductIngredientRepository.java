package com.world.alfs.domain.product_ingredient.repostiory;

import com.world.alfs.domain.product_ingredient.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {

    List<ProductIngredient> findByIngredientId(Long id);
    @Query("SELECT DISTINCT pi.product.id FROM ProductIngredient pi WHERE pi.ingredient.id IN :ingredientIdList")
    List<Long> findDistinctProductIdsByIngredientIds(List<Long> ingredientIdList);

}
