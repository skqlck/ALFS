package com.world.alfs.domain.basket;

import com.world.alfs.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
