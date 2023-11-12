package com.world.alfs.controller.event;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.event.request.EventChooseRequest;
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
    @GetMapping("/{id}")
    ApiResponse<String> getData(@PathVariable Long id) {
        return ApiResponse.ok(eventService.getValue(id));
    }

    //사용자가 event를 눌렀을 떄, 해당 이벤트의 결과를 반환해줘야 한다.
    //이벤트 결과를 redis에 저장하고 있기. 그 값을 다시 DB에 저장
    @PostMapping()
    ApiResponse<Long> choose(EventChooseRequest request){
        EventDto dto = request.toDto();
        //eventService.choose(dto)
        return ApiResponse.ok(1L);
    }
}
