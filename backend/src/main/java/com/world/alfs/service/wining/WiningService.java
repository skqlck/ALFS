package com.world.alfs.service.wining;

import com.world.alfs.domain.wining.repository.WiningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class WiningService {

    private final WiningRepository winingRepository;


}