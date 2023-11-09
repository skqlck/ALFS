package com.world.alfs.domain.manufacturing_allergy;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
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

}
