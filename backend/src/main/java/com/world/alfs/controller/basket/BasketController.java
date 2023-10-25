package com.world.alfs.controller.basket;

import com.world.alfs.service.basket.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
@Slf4j
public class BasketController {

    private final BasketService basketService;

}
