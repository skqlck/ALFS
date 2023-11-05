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
    private String pack;

    @Builder
    public GetBasketResponse(Long basket_id, int count, GetProductListResponse getProductListResponse, String pack) {
        this.basket_id = basket_id;
        this.count = count;
        this.product = getProductListResponse;
        this.pack = pack;
    }
}
