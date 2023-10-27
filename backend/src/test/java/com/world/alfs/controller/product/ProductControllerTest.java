package com.world.alfs.controller.product;

import com.world.alfs.ControllerTestSupport;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.service.product.ProductService;
import com.world.alfs.service.product.dto.AddProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest extends ControllerTestSupport {
    final String URI = "/product";
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @DisplayName("관리자는 상품을 등록할 수 있다.")
    @Test
    void addProduct() throws Exception {
        // given
        AddProductDto productDto = new AddProductDto(); // 적절한 AddProductDto 생성

        given(productService.addProduct(any(AddProductDto.class))).willReturn(1L);
    }

    @DisplayName("상품을 조회할 수 있다.")
    @Test
    void getProduct() throws Exception {

        //given

        //when

        //then

    }

    @DisplayName("상품을 수정할 수 있다.")
    @Test
    void setProduct() throws Exception {

        //given

        //when

        //then

    }

    @DisplayName("상품을 삭제할 수 있다.")
    @Test
    void deleteProduct() throws Exception {

        //given

        //when

        //then

    }


}