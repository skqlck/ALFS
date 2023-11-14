package com.world.alfs.service.event;


import com.world.alfs.domain.event.Event;
import com.world.alfs.domain.event.repository.EventRepository;
import com.world.alfs.service.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Cacheable(value = "myCache", key = "#id")
    public String getValue(Long id) {
        return "Cached Values for ID: " + id;
    }

    public Long choose(EventDto dto) {
        Optional<Event> event = eventRepository.findById(dto.getId());
        event.get().choose(dto.getChoose_case());
        return event.get().getId();
    }
}