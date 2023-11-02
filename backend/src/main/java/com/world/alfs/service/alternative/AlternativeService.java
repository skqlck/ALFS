package com.world.alfs.service.alternative;

import com.world.alfs.controller.alternative.request.GetAlternativeProductAllRequest;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import com.world.alfs.domain.alternative.Alternative;
import com.world.alfs.domain.alternative.repository.AlternativeRepository;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
import com.world.alfs.service.alternative.dto.GetCategoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;
    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;

    // 대체 식품 카테고리 리스트
    public List<CategoryResponse> getCategoryList(GetCategoryListDto dto) {
        List<String> list = alternativeRepository.findByParentIdIn(dto.getIdList()).stream()
                .map(Alternative::getAlternativeName)
                .distinct()
                .collect(Collectors.toList());

        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (String s : list) {
            CategoryResponse response = CategoryResponse.builder()
                    .alternativeName(s)
                    .build();

            categoryResponseList.add(response);
        }

        return categoryResponseList;
    }

    // 카테고리에 해당하는 대체 식품 가져오기
    public List<GetProductListResponse> getAlternativeProduct(String alternativeName) {
        // 원재료명 아이디 찾기
        Optional<Ingredient> ingredient = ingredientRepository.findByName(alternativeName);

        // 원재료명이 포함된 상품 찾기
        List<ProductIngredient> productIngredientList = productIngredientRepository.findByIngredientId(ingredient.get().getId());

        // 상품 테이블에서 상품 목록 가져오기
        List<GetProductListResponse> productListResponse = new ArrayList<>();

        for (ProductIngredient productIngredient : productIngredientList) {
            // 이미지 가져오기
            ProductImg img = productImgRepository.findByProductId(productIngredient.getProduct().getId());
            GetProductListResponse response = productRepository.findById(productIngredient.getProduct().getId()).get().toListResponse(img);
            productListResponse.add(response);
        }

        return productListResponse;
    }

    // 전체 대체 식품 조회
    public List<GetProductListResponse> getAlternativeProductAll(GetAlternativeProductAllDto dto) {
        // 원재료명 아이디 찾기
        List<Long> idList = new ArrayList<>();
        for (String alternative : dto.getAlternativeCategoryList()) {
            Optional<Ingredient> ingredient = ingredientRepository.findByName(alternative);
            idList.add(ingredient.get().getId());
        }

        // 원재료명이 포함된 상품 찾기
        List<Long> list = productIngredientRepository.findDistinctProductIdsByIngredientIds(idList);

        // 상품 테이블에서 상품 목록 가져오기
        List<GetProductListResponse> productListResponse = new ArrayList<>();

        for (Long id : list) {
            ProductImg img = productImgRepository.findByProductId(id);
            GetProductListResponse response = productRepository.findById(id).get().toListResponse(img);
            productListResponse.add(response);
        }

        return productListResponse;
    }

}
