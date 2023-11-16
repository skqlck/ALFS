package com.world.alfs.config.batch.special;

import com.world.alfs.domain.basket.Basket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.EntityManagerFactory;

@Slf4j
public class CustomJpaBasketEndItemProcessor implements ItemProcessor<Basket, Basket> {

    private final EntityManagerFactory entityManagerFactory;

    public CustomJpaBasketEndItemProcessor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Basket process(Basket item) {
        log.info("process");
        item.setIsBigSale(false); // Bakset 객체의 isBigSale true에서 false로 변경
        return item;
    }

}
