package com.world.alfs.domain.product_img;

import com.world.alfs.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column()
    private String img_1;

    @Column()
    private String img_2;

    @Column()
    private String img_3;

    @Builder
    public ProductImg(Long id, Product product, String img_1, String img_2, String img_3) {
        this.id = id;
        this.product = product;
        this.img_1 = img_1;
        this.img_2 = img_2;
        this.img_3 = img_3;
    }

}
