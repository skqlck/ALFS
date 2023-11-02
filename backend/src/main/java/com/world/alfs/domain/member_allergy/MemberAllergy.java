package com.world.alfs.domain.member_allergy;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allergy_id")
    private Allergy allergy;


    @Builder
    public MemberAllergy(Long id, Member member, Allergy allergy) {
        this.id = id;
        this.member = member;
        this.allergy = allergy;
    }
}
