package com.world.alfs.controller.speical.request;

import com.world.alfs.service.speical.dto.AddSpecialQueueDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddSpecialQueueRequest {

    private Long productId;

    private Long memberId;

    public AddSpecialQueueDto toDto() {
        return AddSpecialQueueDto.builder()
                .productId(this.productId)
                .memberId(this.memberId)
                .build();
    }

}
