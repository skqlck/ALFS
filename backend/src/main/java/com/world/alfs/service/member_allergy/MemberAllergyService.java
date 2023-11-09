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

    public Long addMemberAllergy(List<AddMemberAllergyDto> dtoList) {
        // 등록하려는 회원 id
        Long memberId = dtoList.get(0).getMember_id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        // 등록하려는 알러지 타입
        int allergyType = dtoList.get(0).getIsAllergy();

        // 기존 등록된 알러지 정보 삭제
        List<Allergy> allergyList = allergyRepository.findByAllergyType(allergyType);

        for (Allergy a : allergyList) {
            memberAllergyRepository.deleteByMemberIdAndAllergyId(memberId, a.getId());
        }

        // 알러지 등록
        for (AddMemberAllergyDto dto : dtoList) {
        Allergy allergy = allergyRepository.findById(dto.getAllergy_id())
                .orElseThrow(() -> new CustomException(ALLERGY_NOT_FOUND));

            MemberAllergy memberAllergy = dto.toEntity(member,allergy);
            MemberAllergy savedMemberAllergy = memberAllergyRepository.save(memberAllergy);
            log.debug("등록된 알러지 id: {}", savedMemberAllergy.getId());
        }

        return memberId;
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