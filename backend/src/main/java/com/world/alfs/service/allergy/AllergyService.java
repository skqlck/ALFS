package com.world.alfs.service.allergy;

import com.world.alfs.domain.allergy.repository.AllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class AllergyService {
    private final AllergyRepository allergyRepository;
}
