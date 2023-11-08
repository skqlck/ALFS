package com.world.alfs.controller.speical.request;

import com.world.alfs.service.speical.dto.SetSpecialDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SetSpecialReqeust {
    private int status;

    private LocalDateTime start;

    private LocalDateTime end;

    private int count;

    private int salePrice;

    private Long supervisorId;

    public SetSpecialDto toDto() {
        return SetSpecialDto.builder()
                .status(this.status)
                .start(this.start)
                .end(this.end)
                .count(this.count)
                .salePrice(this.salePrice)
                .supervisorId(this.supervisorId)
                .build();
    }
}
