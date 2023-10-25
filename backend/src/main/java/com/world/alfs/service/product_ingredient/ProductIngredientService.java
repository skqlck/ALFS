package com.world.alfs.service.product_ingredient;


import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductIngredientService {

    private final ProductIngredientRepository productIngredientRepository;

}