package com.world.alfs.controller.wining;
import com.world.alfs.service.wining.WiningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wining")
@Slf4j
public class WiningController {
    private final WiningService winingService;
}
