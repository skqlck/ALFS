package com.world.alfs.controller.speical.request;

import com.world.alfs.service.speical.dto.AddSpecialDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AddSpecialReqeust {
    private int status;

    private LocalDateTime start;

    private LocalDateTime end;

    private int count;

    private int salePrice;

    private Long productId;

    private Long supervisorId;

    public AddSpecialDto toDto() {
        return AddSpecialDto.builder()
                .status(this.status)
                .start(this.start)
                .end(this.end)
                .count(this.count)
                .salePrice(this.salePrice)
                .productId(this.productId)
                .supervisorId(this.supervisorId)
                .build();
    }
}
