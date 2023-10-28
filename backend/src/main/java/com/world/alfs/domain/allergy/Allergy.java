package com.world.alfs.domain.allergy;

import com.world.alfs.controller.allergy.response.AllergyResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String allergyName;

    @Column(nullable = false)
    private int allergyType;

    @Builder
    public Allergy(Long id, String allergyName, int allergyType) {
        this.id = id;
        this.allergyName = allergyName;
        this.allergyType = allergyType;
    }

    public AllergyResponse toResponse() {
        return AllergyResponse.builder()
                .id(id)
                .allergyName(allergyName)
                .build();
    }

}
