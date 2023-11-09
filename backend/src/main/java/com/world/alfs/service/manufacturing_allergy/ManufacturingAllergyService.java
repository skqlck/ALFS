package com.world.alfs.service.manufacturing_allergy;

import com.world.alfs.common.exception.CustomException;
import com.world.alfs.common.exception.ErrorCode;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import com.world.alfs.domain.manufacturing_allergy.ManufacturingAllergy;
import com.world.alfs.domain.manufacturing_allergy.repository.ManufacturingAllergyRepository;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.service.manufacturing_allergy.dto.AddManuAllergyDto;
import com.world.alfs.service.manufacturing_allergy.dto.GetManuAllergyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.world.alfs.common.exception.ErrorCode.*;

@Transactional
@Service
@RequiredArgsConstructor
public class ManufacturingAllergyService {

    private final ManufacturingAllergyRepository manufacturingAllergyRepository;
    private final MemberAllergyRepository memberAllergyRepository;
    private final ProductRepository productRepository;
    private final AllergyRepository allergyRepository;

    // 제조시설 알러지 조회
    public boolean getManuAllergy(Long productId, Long memberId) {
        // 회원 알러지 찾기
        List<MemberAllergy> memberAllergyList = memberAllergyRepository.findByMemberId(memberId);

        // 알러지 id만 저장
        List<Long> allergyList = new ArrayList<>();
        for (MemberAllergy memberAllergy : memberAllergyList) {
            allergyList.add(memberAllergy.getAllergy().getId());
        }

        // 제조시설 알러지 테이블에서 해당 상품 중에서 회원 알러지에 해당하는 개수 조회
        int cnt = manufacturingAllergyRepository.findCountByProductAndAllergy(productId, allergyList);

        return cnt > 0;
    }

    // 제조시설 등록
    public Long addManuAllergy(Long productId, List<AddManuAllergyDto> dtoList) {
        List<ManufacturingAllergy>  manufacturingAllergyList = new ArrayList<>();

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->  new CustomException(PRODUCT_NOT_FOUND));

        for (AddManuAllergyDto addManuAllergyDto : dtoList) {
            Allergy allergy = allergyRepository.findByAllergyNameAndAllergyType(addManuAllergyDto.getAllergyName(), 1)
                    .orElseThrow(() -> new CustomException(ALLERGY_NOT_FOUND));

            boolean exists = manufacturingAllergyRepository.existsByProductAndAllergy(product, allergy);

            if (exists) {
                throw new CustomException(DUPLICATE_MANUFACTURING_ALLERGY);
            } else {
                ManufacturingAllergy manufacturingAllergy = ManufacturingAllergy.builder()
                        .product(product)
                        .allergy(allergy)
                        .build();

                manufacturingAllergyList.add(manufacturingAllergy);
            }
        }

        manufacturingAllergyRepository.saveAll(manufacturingAllergyList);

        return productId;
    }

}
