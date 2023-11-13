package com.world.alfs.controller.product;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.controller.product.request.RegisterProductRequest;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.product.Product;
import com.world.alfs.service.allergy.AllergyService;
import com.world.alfs.service.manufacturing_allergy.ManufacturingAllergyService;
import com.world.alfs.service.member_allergy.MemberAllergyService;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import com.world.alfs.service.product.dto.RegisterProductDto;
import com.world.alfs.service.product_img.ProductImgService;
import com.world.alfs.service.product_ingredient.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public Long addProduct(@RequestBody RegisterProductRequest request) {
        RegisterProductDto dto = request.toDto();
        return productService.addProduct(dto);
    }

    @GetMapping("/{id}")
    public ApiResponse<Optional<ProductResponse>> getProduct(@PathVariable Long id) {
        Optional<ProductResponse> savedProduct = productService.getProduct(id);
        return ApiResponse.ok(savedProduct);
    }

    @GetMapping("/cnt")
    public ApiResponse<Long> getProductCnt() {
        Long productCnt = productService.countProduct();
        return ApiResponse.ok(productCnt);
    }

    @GetMapping("/all")
    public ApiResponse<List<GetProductListResponse>> getAllProduct() {
        List<Product> productList = productService.findAll();
        List<GetProductListResponse> response = productService.getAllProduct(productList);
        return ApiResponse.ok(response);
    }

    @GetMapping("category/{memberId}/{category}")
    public ApiResponse<List<GetProductListResponse>> getCategoryProduct(@PathVariable Long memberId, @PathVariable int category) {
        List<Product> product_list = productService.getCategoryProduct(category); //카데고리에 해당하는 상품 객체 가져오기
        List<GetProductListResponse> response = productService.getAllProduct(product_list);
        allergy_filter(product_list,response,memberId);
        return ApiResponse.ok(response);
    }

    @GetMapping("/all/{memberId}/{page}")
    public ApiResponse<List<GetProductListResponse>> getAllProduct(@PathVariable Long memberId, @PathVariable Integer page) {
        List<Product> product_list = productService.getAllProductId(productService.countPage(), page);
        List<GetProductListResponse> response = productService.getAllProduct(product_list);
        allergy_filter(product_list,response,memberId);
        return ApiResponse.ok(response);
    }

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


//    - - - - - - - - - - 비즈니스 로직 - - - - - - - - - -- - -
    public void allergy_filter(List<Product> product_list, List<GetProductListResponse> response, Long memberId) {

        for (int i = 0; i < product_list.size(); i++) {
            List<String> compare_ingredient = new ArrayList<>();
            List<Ingredient> product_ingredient_list = productIngredientService.getAllIngredientId(product_list.get(i).getId());
            for (int a = 0; a < product_ingredient_list.size(); a++) {
                compare_ingredient.add(product_ingredient_list.get(a).getName());
            }
            List<Long> memberAllergy_allergy_id_list = memberAllergyService.getFilteredAllergyId(memberId);
            Set<Integer> FilterCode = new HashSet<>();

            for (int a = 0; a < memberAllergy_allergy_id_list.size(); a++) {
                Allergy allergy = allergyService.getAllergy(memberAllergy_allergy_id_list.get(a));
                for (int b = 0; b < compare_ingredient.size(); b++) {
                    if (compare_ingredient.get(b).equals(allergy.getAllergyName())) {
                        FilterCode.add(allergy.getAllergyType());
                    }
                }
            }

            // 제조시설 필터 코드 추가
            boolean isManuAllergy = manufacturingAllergyService.getManuAllergy(product_list.get(i).getId(), memberId);
            if (isManuAllergy) {
                FilterCode.add(2);
            }

            if (FilterCode.isEmpty()) {
                FilterCode.add(3);
            }
            response.get(i).setCode(FilterCode);
        }

    }


}
