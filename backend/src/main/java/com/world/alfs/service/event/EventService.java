package com.world.alfs.service.event;


import com.world.alfs.domain.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Cacheable(value = "myCache", key = "#id")
    public String getValue(Long id) {
        return "Cached Values for ID: " + id;
    }

//    public Long choose(Long id) {
//    }
}