package com.world.alfs.config.batch.special;

import com.world.alfs.domain.event.Event;
import com.world.alfs.domain.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.EntityManagerFactory;

@Slf4j
public class CustomJpaEndItemProcessor2 implements ItemProcessor<Event, Event> {

    private final EntityManagerFactory entityManagerFactory;

    public CustomJpaEndItemProcessor2(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Event process(Event item) {
        log.info("process");
        item.setStatus(2);
        return item;
    }
}
