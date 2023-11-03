package com.world.alfs.service.manufacturing_allergy;

import com.world.alfs.domain.manufacturing_allergy.repository.ManufacturingAllergyRepository;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.service.manufacturing_allergy.dto.GetManuAllergyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ManufacturingAllergyService {

    private final ManufacturingAllergyRepository manufacturingAllergyRepository;
    private final MemberAllergyRepository memberAllergyRepository;

    public boolean getManuAllergy(GetManuAllergyDto dto) {
        // 회원 알러지 찾기
        List<MemberAllergy> memberAllergyList = memberAllergyRepository.findByMemberId(dto.getMemberId());

        // 알러지 id만 저장
        List<Long> allergyList = new ArrayList<>();
        for (MemberAllergy memberAllergy : memberAllergyList) {
            allergyList.add(memberAllergy.getAllergy().getId());
        }

        // 제조시설 알러지 테이블에서 해당 상품 중에서 회원 알러지에 해당하는 개수 조회
        int cnt = manufacturingAllergyRepository.findCountByProductAndAllergy(dto.getProductId(), allergyList);

        return cnt > 0;
    }

}
