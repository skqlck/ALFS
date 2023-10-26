package com.world.alfs.service.alternative;

import com.world.alfs.domain.alternative.repository.AlternativeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AlternativeService {
    private final AlternativeRepository alternativeRepository;
}
