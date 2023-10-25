package com.world.alfs.domain.product_ingredient.repostiory;

import com.world.alfs.domain.product_ingredient.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {

}
