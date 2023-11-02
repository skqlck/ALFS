package com.world.alfs.controller.basket.response;

import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_img.ProductImg;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetBasketResponse {
    private Long basket_id;
    private int count;
    private GetProductListResponse product;

    @Builder
    public GetBasketResponse(Long basket_id, int count, GetProductListResponse getProductListResponse) {
        this.basket_id = basket_id;
        this.count = count;
        this.product = getProductListResponse;
    }
}
