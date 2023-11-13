package com.world.alfs.service.speical.dto;

import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.wining.Wining;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddSpecialQueueDto {

    private Long productId;

    private Long memberId;

    public Wining toEntity(Special special, Member member) {
        return Wining.builder()
                .special(special)
                .member(member)
                .build();
    }
}
