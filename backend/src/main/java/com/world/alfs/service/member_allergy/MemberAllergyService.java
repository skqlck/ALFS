package com.world.alfs.service.member_allergy;

import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberAllergyService {

    private final MemberAllergyRepository memberAllergyRepository;

}