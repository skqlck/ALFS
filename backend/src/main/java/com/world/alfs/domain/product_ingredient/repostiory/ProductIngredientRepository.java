package com.world.alfs.domain.product_ingredient.repostiory;

import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {

    List<ProductIngredient> findByIngredient_Id(Long id);

}
