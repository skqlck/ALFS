package com.world.alfs.service.ingredient;

import com.world.alfs.common.exception.CustomException;
import com.world.alfs.common.exception.ErrorCode;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import com.world.alfs.service.ingredient.dto.AddIngredientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.world.alfs.common.exception.ErrorCode.DUPLICATE_PRODUCT_INGREDIENT;
import static com.world.alfs.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;
    private final ProductRepository productRepository;

    public List<Long> checkIngredient(List<String> productList) {
        List<Long>list = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (ingredientRepository.findByName(productList.get(i)).isEmpty()) {
                Ingredient ingredient = Ingredient.builder()
                        .name(productList.get(i))
                        .build();
                ingredientRepository.save(ingredient);
            }
            Optional<Ingredient> savedIngredient = ingredientRepository.findByName(productList.get(i));
            list.add(savedIngredient.get().getId());
        }
        return list;
    }

    // 상품의 원재료 등록
    public Long addIngredient(Long productId, List<AddIngredientDto> dtoList) {

        List<Ingredient> ingredientList = new ArrayList<>();
        HashSet<String> ingredientSet = new HashSet<>();

        // 원재료 테이블에 원재료 넣기 (중복 제외)
        for (AddIngredientDto dto : dtoList) {
            ingredientSet.add(dto.getIngredientName());
            boolean exists = ingredientRepository.existsByName(dto.getIngredientName());

            if (!exists) {
                Ingredient ingredient = dto.toEntity();
                ingredientList.add(ingredient);
            }
        }
        ingredientRepository.saveAll(ingredientList);

        // 상품-원재료 관계 연결
        List<ProductIngredient> productIngredientList = new ArrayList<>();

        for (String ingredientName : ingredientSet) {
            Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                    .orElseThrow(() -> new CustomException(ErrorCode.INGREDIENT_NOT_FOUND));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

            boolean exists = productIngredientRepository.existsByProductIdAndIngredientId(productId, ingredient.getId());
            if (exists) {
                throw new CustomException(DUPLICATE_PRODUCT_INGREDIENT);
            } else {
                ProductIngredient productIngredient = product.toProductIngredient(ingredient);
                productIngredientList.add(productIngredient);
            }
        }

        productIngredientRepository.saveAll(productIngredientList);

        return productId;
    }

}
