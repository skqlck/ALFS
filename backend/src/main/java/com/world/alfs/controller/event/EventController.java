package com.world.alfs.controller.event;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.event.request.EventChooseRequest;
import com.world.alfs.controller.event.response.EventResponse;
import com.world.alfs.controller.event.response.GetEventResponse;
import com.world.alfs.service.event.EventService;
import com.world.alfs.service.event.dto.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    @GetMapping()
    ApiResponse<GetEventResponse> getData() {
        return ApiResponse.ok(eventService.findEvent());
    }

    @PostMapping()
    ApiResponse<EventResponse> choose(@RequestBody EventChooseRequest request){
        EventDto dto = request.toDto();
        return ApiResponse.ok(eventService.choose(dto));
    }
}
