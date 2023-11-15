package com.world.alfs.domain.event.repository;

import com.world.alfs.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStart(LocalDateTime parsedDateTime);

    List<Event> findByEnd(LocalDateTime parsedDateTime);
}
