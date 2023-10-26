package com.world.alfs.controller.alternative;

import com.world.alfs.service.alternative.AlternativeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alternative")
@Slf4j
public class AlternativeController {

    private final AlternativeService alternativeService;

}
