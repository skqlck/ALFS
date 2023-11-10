package com.world.alfs.controller.product;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.product.Product;
import com.world.alfs.service.Ingredient_allergy.IngredientAllergyService;
import com.world.alfs.service.allergy.AllergyService;
import com.world.alfs.service.ingredient.IngredientService;
import com.world.alfs.service.manufacturing_allergy.ManufacturingAllergyService;
import com.world.alfs.service.member_allergy.MemberAllergyService;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import com.world.alfs.service.product_img.ProductImgService;
import com.world.alfs.service.product_ingredient.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductImgService productImgService;
    private final ProductIngredientService productIngredientService;
    private final MemberAllergyService memberAllergyService;
    private final AllergyService allergyService;
    private final ManufacturingAllergyService manufacturingAllergyService;

    @PostMapping()
    public Long addProduct(@RequestBody AddProductRequest request) {
        AddProductDto dto = request.toDto();
        return productService.addProduct(dto);
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<ProductResponse>> getProduct(@PathVariable Long id) {
        Optional<ProductResponse> savedProduct = productService.getProduct(id);
        return ApiResponse.ok(savedProduct);
    }

    @GetMapping("/all")
    public ApiResponse<List<GetProductListResponse>> getAllProduct() {
        List<GetProductListResponse> response = productService.getAllProduct();
        return ApiResponse.ok(response);
    }

    @GetMapping("/all/{memberId}")
    public ApiResponse<List<GetProductListResponse>> getAllProduct(@PathVariable Long memberId) {
//        List<Long> product_list = productService.getAllProductId();
        List<GetProductListResponse> response = productService.getAllProduct();

        response.forEach(productResponse -> {
            List<String> compareIngredient = productIngredientService.getAllIngredientId(productResponse.getId())
                    .stream()
                    .map(Ingredient::getName)
                    .collect(Collectors.toList());

            List<Long> memberAllergyAllergyIdList = memberAllergyService.getFilteredAllergyId(memberId);
            Set<Integer> filterCode = memberAllergyAllergyIdList.stream()
                    .map(allergyService::getAllergy)
                    .filter(allergy -> compareIngredient.contains(allergy.getAllergyName()))
                    .map(Allergy::getAllergyType)
                    .collect(Collectors.toSet());

            productResponse.setCode(filterCode);
        });

//        for (int i = 0; i < product_list.size(); i++) {
//            List<String> compare_ingredient = new ArrayList<>();
//            List<Ingredient> product_ingredient_list = productIngredientService.getAllIngredientId(product_list.get(i));
//            for (int a = 0; a < product_ingredient_list.size(); a++) {
//                compare_ingredient.add(product_ingredient_list.get(a).getName());
//            }
//            List<Long> memberAllergy_allergy_id_list = memberAllergyService.getFilteredAllergyId(memberId);
//            Set<Integer> FilterCode = new HashSet<>();
//            for (int a = 0; a < memberAllergy_allergy_id_list.size(); a++) {
//                Allergy allergy = allergyService.getAllergy(memberAllergy_allergy_id_list.get(a));
//                for (int b = 0; b < compare_ingredient.size(); b++) {
//                    if (compare_ingredient.get(b).equals(allergy.getAllergyName())) {
//                        FilterCode.add(allergy.getAllergyType());
//                    }
//                }
//            }
//
//            // 제조시설 알러지 필터 체크
//            boolean isManuAllergy = manufacturingAllergyService.getManuAllergy(product_list.get(i), memberId);
//            if (isManuAllergy) {
//                FilterCode.add(2);
//            }
//
//            //FilterCode 중복 제거
//            response.get(i).setCode(FilterCode);
//        }
        return ApiResponse.ok(response);
    }


//    @GetMapping("/all/{memberId}")
//    public ApiResponse<List<GetProductListResponse>> getAllProduct(@PathVariable Long memberId) {
//        List<Long> product_list = productService.getAllProductId();
//        List<GetProductListResponse> response = productService.getAllProduct();
//        for (int i = 0; i < product_list.size(); i++) {
//            List<String> compare_ingredient = new ArrayList<>();
//            List<Ingredient> product_ingredient_list = productIngredientService.getAllIngredientId(product_list.get(i));
//            for (int a = 0; a < product_ingredient_list.size(); a++) {
//                compare_ingredient.add(product_ingredient_list.get(a).getName());
//            }
//            List<Long> memberAllergy_allergy_id_list = memberAllergyService.getFilteredAllergyId(memberId);
//            Set<Integer> FilterCode = new HashSet<>();
//            for (int a = 0; a < memberAllergy_allergy_id_list.size(); a++) {
//                Allergy allergy = allergyService.getAllergy(memberAllergy_allergy_id_list.get(a));
//                for (int b = 0; b < compare_ingredient.size(); b++) {
//                    if (compare_ingredient.get(b).equals(allergy.getAllergyName())) {
//                        FilterCode.add(allergy.getAllergyType());
//                    }
//                }
//            }
//            //FilterCode 중복 제거
//            response.get(i).setCode(FilterCode);
//        }
//        return ApiResponse.ok(response);
//    }

    @PatchMapping()
    public ApiResponse<Long> setProduct(@RequestBody AddProductRequest request) {
        AddProductDto dto = request.toDto();
        Long savedId = productService.setProduct(dto);
        return ApiResponse.ok(savedId);

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Long> deleteProduct(@PathVariable Long id) {
        productImgService.deleteProductImg(id);
        Long savedProductId = productService.deleteProduct(id);
        return ApiResponse.ok(savedProductId);
    }
}
