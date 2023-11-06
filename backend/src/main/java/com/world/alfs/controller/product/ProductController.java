package com.world.alfs.controller.product;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.service.Ingredient_allergy.IngredientAllergyService;
import com.world.alfs.service.allergy.AllergyService;
import com.world.alfs.service.member_allergy.MemberAllergyService;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import com.world.alfs.service.product_ingredient.ProductIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductIngredientService productIngredientService;
    private final IngredientAllergyService ingredientAllergyService;
    private final MemberAllergyService memberAllergyService;
    private final AllergyService allergyService;

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

    @GetMapping("/all/{memberId}")
    public ApiResponse<List<GetProductListResponse>> getAllProduct(@PathVariable Long memberId) {
        List<Long> product_list = productService.getAllProductId();
        List<GetProductListResponse> response = productService.getAllProduct();
        for (int i = 0; i < product_list.size(); i++) {
            //step 1. product_id로 product_ingredient 로 [ingredient] 를 조회하여 ingredient_id를 반환한다.
            List<Long> ingredient_list = productIngredientService.getAllIngredientId(product_list.get(i));
            System.out.println("step1 pass");

            //step 2. [ingredient] 로 [ingredient_allergy]의 allergy_id를 조회한다.
            List<Long> allergy_list = ingredientAllergyService.getAllAllergyId(ingredient_list);
            System.out.println("step2 pass");

            //step 3. allergy_id와 동일하고 member_id가 같은 컬럼을 [member_allergy]에서 조회한다.
            List<Long> member_allergy_list = memberAllergyService.getFilteredAllergyId(memberId, allergy_list);
            System.out.println("step3 pass");

            //step 3. 여기서 조회된(필터된) allergy_id를 가지고 [allergy]의 allergy_type 을 반환한다.
            List<Integer> FilterCode = allergyService.getAllergyType(member_allergy_list);
            System.out.println("step4 pass");

            //step 4. response 에 allergy_type 을 세팅한다.
            response.get(i).setCode(FilterCode);
            System.out.println("step5 pass");
        }
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
        Long savedId = productService.deleteProduct(id);
        return ApiResponse.ok(savedId);
    }
}
