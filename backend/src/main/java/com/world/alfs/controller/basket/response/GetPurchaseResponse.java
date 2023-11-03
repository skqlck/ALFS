package com.world.alfs.controller.basket.response;

import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_img.ProductImg;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPurchaseResponse {
    private Long basket_id;
    private int count;
    private int purchase;
    private GetProductListResponse product;

    @Builder
    public GetPurchaseResponse(Long basket_id, int count, int totalPrice, GetProductListResponse getProductListResponse) {
        this.basket_id = basket_id;
        this.count = count;
        this.purchase = totalPrice;
        this.product = getProductListResponse;
    }
}
