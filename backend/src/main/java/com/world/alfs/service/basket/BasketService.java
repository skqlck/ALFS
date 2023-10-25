package com.world.alfs.service.basket;

import com.world.alfs.domain.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BasketService {
    private final BasketRepository basketRepository;

}
