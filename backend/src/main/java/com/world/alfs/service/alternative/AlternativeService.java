package com.world.alfs.service.alternative;

import com.world.alfs.common.exception.CustomException;
import com.world.alfs.controller.alternative.response.CategoryResponse;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.domain.alternative.Alternative;
import com.world.alfs.domain.alternative.repository.AlternativeRepository;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.ingredient.repository.IngredientRepository;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.domain.product_ingredient.repostiory.ProductIngredientRepository;
import com.world.alfs.service.alternative.dto.GetAlternativeProductAllDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.world.alfs.common.exception.ErrorCode.INGREDIENT_NOT_FOUND;
import static com.world.alfs.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

@Transactional
@Service
@RequiredArgsConstructor
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;
    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;
    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final MemberAllergyRepository memberAllergyRepository;

    // 대체 식품 카테고리 리스트
    public List<CategoryResponse> getCategoryList(Long memberId) {
        List<MemberAllergy> memberAllergyList = memberAllergyRepository.findByMemberId(memberId);

        List<String> allergyIdList = new ArrayList<>();
        for (MemberAllergy memberAllergy : memberAllergyList) {
            allergyIdList.add(memberAllergy.getAllergy().getId().toString());
        }

        List<String> list = alternativeRepository.findByParentIdIn(allergyIdList).stream()
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
        Ingredient ingredient = ingredientRepository.findByName(alternativeName)
                .orElseThrow(() -> new CustomException(INGREDIENT_NOT_FOUND));

        // 원재료명이 포함된 상품 찾기
        List<ProductIngredient> productIngredientList = productIngredientRepository.findByIngredientId(ingredient.getId());

        // 상품 테이블에서 상품 목록 가져오기
        List<GetProductListResponse> productListResponse = new ArrayList<>();

        for (ProductIngredient productIngredient : productIngredientList) {
            // 이미지 가져오기
            ProductImg img = productImgRepository.findByProductId(productIngredient.getProduct().getId());
            GetProductListResponse response = productRepository.findById(productIngredient.getProduct().getId()).get().toListResponse(img,null);
            productListResponse.add(response);
        }

        return productListResponse;
    }

    // 전체 대체 식품 조회
    public List<GetProductListResponse> getAlternativeProductAll(GetAlternativeProductAllDto dto) {
        // 원재료명 아이디 찾기
        List<Long> idList = new ArrayList<>();
        for (String alternative : dto.getAlternativeCategoryList()) {
            Ingredient ingredient = ingredientRepository.findByName(alternative)
                            .orElseThrow(() -> new CustomException(INGREDIENT_NOT_FOUND));
            idList.add(ingredient.getId());
        }

        // 원재료명이 포함된 상품 찾기
        List<Long> list = productIngredientRepository.findDistinctProductIdsByIngredientIds(idList);

        // 상품 테이블에서 상품 목록 가져오기
        List<GetProductListResponse> productListResponse = new ArrayList<>();

        for (Long id : list) {
            ProductImg img = productImgRepository.findByProductId(id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));
            GetProductListResponse response = product.toListResponse(img,null);
            productListResponse.add(response);
        }

        return productListResponse;
    }

}
