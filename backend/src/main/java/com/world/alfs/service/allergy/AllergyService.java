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

    public List<AddMemberAllergyDto> checkAllergyName(Long memberId, List<String> NameList, int isAllergy) {
        List<AddMemberAllergyDto>list = new ArrayList<>();
        for(int i=0; i<NameList.size(); i++){
            Optional<Allergy> allergy = allergyRepository.findByAllergyNameAndAllergyType(NameList.get(i),isAllergy);
            if(allergy ==null){
                Allergy addAllergy = Allergy.builder()
                        .allergyName(NameList.get(i))
                        .allergyType(isAllergy)
                        .build();
                allergyRepository.save(addAllergy);

            }
            Allergy savedAllergy = allergyRepository.findByAllergyName(NameList.get(i));
            Long allergyId = savedAllergy.getId();

            AddMemberAllergyDto dto = AddMemberAllergyDto.builder()
                    .allergy_id(allergyId)
                    .member_id(memberId)
                    .build();
            System.out.println("여기는 AllergyService  (member_id,allergy_id) "+dto.getMember_id() +" "+dto.getAllergy_id());
            list.add(dto);
        }
        return list;
    }

    public List<Integer> getAllergyType(List<Long> memberAllergyList) {
        List<Integer> list = new ArrayList<>();
        for(Long memberAllergyId : memberAllergyList){
            list.add(allergyRepository.findAllergyTypeById(memberAllergyId));
        }
        //list 에서 중복 값 빼고 반환하기.

        return list;
    }

    public String getAllergyName(Long allergyId) {
        Optional<Allergy> allergy = allergyRepository.findById(allergyId);
        return allergy.get().getAllergyName();
    }

    public Allergy getAllergy(Long allergyId) {
        Optional<Allergy> allergy = allergyRepository.findById(allergyId);
        return allergy.get();
    }
}
