package com.world.alfs.controller.basket.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBasketRequest {
    private Long member_id;
    private Long product_id;
    private int count;

    @Builder
    public AddBasketRequest(Long member_id, Long product_id, int count) {
        this.member_id = member_id;
        this.product_id = product_id;
        this.count = count;
    }
}
