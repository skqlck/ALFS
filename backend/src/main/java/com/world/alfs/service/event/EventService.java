package com.world.alfs.service.event;


import com.world.alfs.controller.event.response.EventResponse;
import com.world.alfs.controller.event.response.GetEventResponse;
import com.world.alfs.domain.event.Event;
import com.world.alfs.domain.event.repository.EventRepository;
import com.world.alfs.service.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;
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

    public EventResponse choose(EventDto dto) {
        Optional<Event> event = eventRepository.findById(dto.getId());
        event.get().choose(dto.getChoose_case());
        DecimalFormat df = new DecimalFormat("#.#");
        float case1_rate = event.get().getCase1_cnt();
        float case2_rate = event.get().getCase2_cnt();
        case1_rate = (case1_rate / (case1_rate + case2_rate)) * 100;
        case2_rate = 100 - case1_rate;
        return EventResponse.builder()
                .case1_rate((int)case1_rate)
                .case2_rate((int)case2_rate)
                .build();

    }

    @GetMapping()
    public Optional<Event> getEvent(Long id) {
        return eventRepository.findById(id);
    }

    public GetEventResponse findEvent() {
        Event event = eventRepository.findByStatus(1);
        return event.toResponse();
    }
}