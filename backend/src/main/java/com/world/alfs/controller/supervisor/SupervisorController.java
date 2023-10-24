package com.world.alfs.controller.supervisor;

import com.world.alfs.service.supervisor.SupervisorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisor")
@Slf4j
public class SupervisorController {

    private final SupervisorService supervisorService;

}
