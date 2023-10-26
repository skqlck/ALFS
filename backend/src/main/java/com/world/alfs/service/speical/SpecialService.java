package com.world.alfs.service.speical;

import com.world.alfs.domain.special.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SpecialService {

    private final SpecialRepository specialRepository;


}