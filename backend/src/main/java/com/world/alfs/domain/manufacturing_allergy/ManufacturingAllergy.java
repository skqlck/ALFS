package com.world.alfs.domain.manufacturing_allergy;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManufacturingAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "allergy_id")
    private Allergy allergy;

    @Builder
    public ManufacturingAllergy(Long id, Product product, Allergy allergy) {
        this.id = id;
        this.product = product;
        this.allergy = allergy;
    }

}
