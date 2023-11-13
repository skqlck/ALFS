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
    private String date;
    private GetProductListResponse product;

    @Builder
    public GetPurchaseResponse(Long basket_id, int count, int totalPrice, String date, GetProductListResponse getProductListResponse) {
        this.basket_id = basket_id;
        this.count = count;
        this.purchase = totalPrice;
        this.date = date;
        this.product = getProductListResponse;
    }
}
