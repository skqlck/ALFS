package com.world.alfs.config.batch.special;

import com.world.alfs.domain.event.Event;
import com.world.alfs.domain.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.EntityManagerFactory;


@Slf4j
public class CustomJpaStartItemProcessor2 implements ItemProcessor<Event, Event> {
    private final EntityManagerFactory entityManagerFactory;
    public CustomJpaStartItemProcessor2(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public Event process(Event item) {
        log.info("process");
        item.setStatus(1); // Special 객체의 status를 0에서 1로 변경
        return item;
    }
}
