package com.world.alfs.controller.event;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    ApiResponse<String> getData(@PathVariable Long id) {
        return ApiResponse.ok(eventService.getValue(id));
    }


}
