package com.world.alfs.domain.wining;

import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.special.Special;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Special special;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
