package com.world.alfs.controller.basket.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberBasketRequest {
    private Long member_id;
    private Long basket_id;

    @Builder
    public MemberBasketRequest(Long member_id, Long basket_id) {
        this.member_id = member_id;
        this.basket_id = basket_id;
    }
}
