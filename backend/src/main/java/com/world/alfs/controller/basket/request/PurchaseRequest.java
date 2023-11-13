package com.world.alfs.controller.basket.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PurchaseRequest {

    private Long member_id;
    private List<Long> basket_ids;

    @Builder
    public PurchaseRequest(Long member_id, List<Long> basket_ids) {
        this.member_id = member_id;
        this.basket_ids = basket_ids;
    }
}
