package com.world.alfs.domain.product_ingredient;

import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Builder
    public ProductIngredient(Long id, Product product, Ingredient ingredient) {
        this.id = id;
        this.product = product;
        this.ingredient = ingredient;
    }

}
