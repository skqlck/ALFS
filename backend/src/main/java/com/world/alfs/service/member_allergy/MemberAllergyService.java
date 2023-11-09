package com.world.alfs.service.member_allergy;

import com.world.alfs.common.exception.CustomException;
import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.world.alfs.common.exception.ErrorCode.ALLERGY_NOT_FOUND;
import static com.world.alfs.common.exception.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class MemberAllergyService {

    private final MemberAllergyRepository memberAllergyRepository;
    private final AllergyRepository allergyRepository;
    private final MemberRepository memberRepository;

    public Long addMemberAllergy(AddMemberAllergyDto dto) {
        Member member = memberRepository.findById(dto.getMember_id())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        Allergy allergy = allergyRepository.findById(dto.getAllergy_id())
                .orElseThrow(() -> new CustomException(ALLERGY_NOT_FOUND));

        boolean exists = memberAllergyRepository.existsByMemberIdAndAllergyId(dto.getMember_id(), dto.getAllergy_id());

        if (!exists) {
            MemberAllergy memberAllergy = dto.toEntity(member,allergy);
            MemberAllergy savedMemberAllergy = memberAllergyRepository.save(memberAllergy);

            return savedMemberAllergy.getId();
        } else {
            return -1L;
        }
    }

    public List<Long> getFilteredAllergyId(Long memberId) {
        List<Long> list = new ArrayList<>();
        List<MemberAllergy> memberAllergyList = memberAllergyRepository.findByMemberId(memberId);
        for(MemberAllergy ma : memberAllergyList){
            list.add(ma.getAllergy().getId());
        }
        return list;
    }

    public List<AllergyResponse> getMemberAllergy(Long memberId, int isAllergy) {
        List<MemberAllergy> memberAllergyList = memberAllergyRepository.findByMemberId(memberId);

        List<AllergyResponse> allergyResponseList = new ArrayList<>();

        for (MemberAllergy memberAllergy : memberAllergyList) {
            Optional<Allergy> allergy = allergyRepository.findByIdAndAllergyType(memberAllergy.getAllergy().getId(), isAllergy);
            if (allergy.isEmpty()) {
                continue;
            }
            AllergyResponse allergyResponse = allergy.get().toResponse();
            allergyResponseList.add(allergyResponse);
        }

        return allergyResponseList;
    }

}