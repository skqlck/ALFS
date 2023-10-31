package com.world.alfs.service.allergy;

import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
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
            allergyResponse = a.toResponse();
            allergyResponseList.add(allergyResponse);
        }

        return allergyResponseList;
    }

    public Boolean checkAllergyName(Long member_id, List<String> NameList, int isAllergy) {
        for(int i=0; i<NameList.size(); i++){
            Allergy allergy = allergyRepository.findByAllergyNameAndAllergyType(NameList.get(i),isAllergy);
            if(allergy ==null){
                Allergy addAllergy = Allergy.builder()
                        .allergyName(NameList.get(i))
                        .allergyType(isAllergy)
                        .build();
                allergyRepository.save(addAllergy);

            }
            Allergy savedAllergy = allergyRepository.findByAllergyName(NameList.get(i));
            Long allergyId = savedAllergy.getId();

//            id, member_id, allergyId를  AddMemberAllergyRequest 에 담아서
            AddMemberAllergyDto dto = AddMemberAllergyDto.builder()
                    .allergy_id(allergyId)
                    .member_id(member_id)
                    .build();
//            addMemberAllergy -> Redirect


        }

        return true;
    }

}
