package com.world.alfs.domain.event.repository;

import com.world.alfs.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
