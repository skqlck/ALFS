package com.world.alfs.config.batch.special;

import com.world.alfs.domain.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.EntityManagerFactory;


@Slf4j
public class CustomJpaEndItemProcessor implements ItemProcessor<Special, Special> {

    private final EntityManagerFactory entityManagerFactory;

    public CustomJpaEndItemProcessor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Special process(Special item) {
        log.info("process");
        item.setStatus(2); // Special 객체의 status를 1에서 2로 변경
        return item;
    }
}
