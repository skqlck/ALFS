package com.world.alfs.service.product_ingredient;


import com.world.alfs.controller.ApiResponse;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductIngredientService {

    private final ProductIngredientRepository productIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    public Long addProductIngredient(Long productId, Long ingredientId){
        ProductIngredient productIngredient = ProductIngredient.builder()
                .product(productRepository.findById(productId).get())
                .ingredient(ingredientRepository.findById(ingredientId).get())
                .build();
        ProductIngredient savedProductIngredient = productIngredientRepository.save(productIngredient);
        return savedProductIngredient.getId();
    }

}