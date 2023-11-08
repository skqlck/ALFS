package com.world.alfs.domain.allergy;

import com.world.alfs.controller.allergy.response.AllergyResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String allergyName;

    @Column(nullable = false)
    private int allergyType;


//    public Allergy(Long id, String allergyName, int allergyType) {
//        this.id = id;
//        this.allergyName = allergyName;
//        this.allergyType = allergyType;
//    }

    public AllergyResponse toResponse() {
        return AllergyResponse.builder()
                .id(id)
                .allergyName(allergyName)
                .build();
    }

}
