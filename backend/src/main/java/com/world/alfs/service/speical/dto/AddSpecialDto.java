package com.world.alfs.service.speical.dto;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.supervisor.Supervisor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AddSpecialDto {

    private int status;

    private LocalDateTime start;

    private LocalDateTime end;

    private int count;

    private int salePrice;

    private Long productId;

    private Long supervisorId;

    @Builder
    public AddSpecialDto(int status, LocalDateTime start, LocalDateTime end, int count, int salePrice, Long productId, Long supervisorId){
        this.status = status;
        this.start = start;
        this.end = end;
        this.count = count;
        this.salePrice = salePrice;
        this.productId = productId;
        this.supervisorId = supervisorId;
    }

    public Special toEntity(Product product, Supervisor supervisor) {
        return Special.builder()
                .status(this.status)
                .start(this.start)
                .end(this.end)
                .count(this.count)
                .salePrice(this.salePrice)
                .product(product)
                .supervisor(supervisor)
                .build();
    }
}
