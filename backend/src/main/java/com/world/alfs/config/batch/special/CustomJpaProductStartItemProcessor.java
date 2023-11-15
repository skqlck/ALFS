package com.world.alfs.config.batch.special;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.EntityManagerFactory;


@Slf4j
public class CustomJpaProductStartItemProcessor implements ItemProcessor<Product, Product> {
    private final EntityManagerFactory entityManagerFactory;
    private final SpecialRepository specialRepository;

    public CustomJpaProductStartItemProcessor(EntityManagerFactory entityManagerFactory, SpecialRepository specialRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.specialRepository = specialRepository;
    }

    @Override
    public Product process(Product item) {
        log.info("process");
        Special special = specialRepository.findByProductId(item.getId());
        item.setSale(special.getSalePrice()); // Product 객체의 sale을 기존 가격에서 특가할인가격으로 변경
        return item;
    }
}
