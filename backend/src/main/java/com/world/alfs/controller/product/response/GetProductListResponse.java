package com.world.alfs.controller.product.response;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_img.ProductImg;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GetProductListResponse {
    private Long id;

    private String name;

    private String title;

    private int price;

    private int sale;

    private String img;

    @Builder
    public GetProductListResponse(Long id, String name, String title, int price, int sale, String img) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.img = img;
    }

    public GetProductListResponse toGetProductListResponse(Product product){
        return GetProductListResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .title(product.getTitle())
                .price(product.getPrice())
                .sale(product.getSale())
//                .img(productImg)
                .build();
    }
}
