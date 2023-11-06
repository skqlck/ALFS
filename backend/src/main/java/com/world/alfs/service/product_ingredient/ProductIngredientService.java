package com.world.alfs.service.product_ingredient;


import com.world.alfs.controller.ApiResponse;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductIngredientService {

    private final ProductIngredientRepository productIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    public Long addProductIngredient(Long productId, Long ingredientId) {
        ProductIngredient productIngredient = ProductIngredient.builder()
                .product(productRepository.findById(productId).get())
                .ingredient(ingredientRepository.findById(ingredientId).get())
                .build();
        ProductIngredient savedProductIngredient = productIngredientRepository.save(productIngredient);
        return savedProductIngredient.getId();
    }

    public List<Ingredient> getAllIngredientId(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        System.out.println(product.get().getId());
        List<ProductIngredient> list = productIngredientRepository.findAllByProduct(product.get());
        List<Ingredient> response = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            Optional<Ingredient> ingredient = ingredientRepository.findById(list.get(i).getIngredient().getId());
            response.add(ingredient.get());
        }
        return response;
    }
}