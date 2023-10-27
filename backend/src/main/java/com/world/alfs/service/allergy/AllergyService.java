package com.world.alfs.service.allergy;

import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AllergyService {

    private final AllergyRepository allergyRepository;

    // 22가지 알러지 원인 식품 리스트
    public List<AllergyResponse> getAllergyList() {
        Optional<List<Allergy>> allergyList = Optional.ofNullable(allergyRepository.findTop22ByOrderById());

        if (allergyList.isEmpty()) {
            return null;
        }

        List<AllergyResponse> allergyResponseList = new ArrayList<>();
        AllergyResponse allergyResponse = null;

        for(Allergy a : allergyList.get()) {
            allergyResponse = AllergyResponse.builder()
                    .id(a.getId())
                    .allergyName(a.getAllergyName())
                    .build();
            allergyResponseList.add(allergyResponse);
        }

        return allergyResponseList;
    }

}
