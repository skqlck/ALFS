package com.world.alfs.service.member_allergy;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.allergy.repository.AllergyRepository;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.domain.member_allergy.MemberAllergy;
import com.world.alfs.domain.member_allergy.repository.MemberAllergyRepository;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberAllergyService {

    private final MemberAllergyRepository memberAllergyRepository;
    private final AllergyRepository allergyRepository;
    private final MemberRepository memberRepository;

    public Long addMemberAllergy(AddMemberAllergyDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getMember_id());
        Optional<Allergy> allergy = allergyRepository.findById(dto.getAllergy_id());
        MemberAllergy memberAllergy = dto.toEntity(member.get(),allergy.get());
        MemberAllergy savedMemberAllergy = memberAllergyRepository.save(memberAllergy);
        return savedMemberAllergy.getId();
    }

    public List<Long> getFilteredAllergyId(Long memberId, List<Long> allergyList) {
        List<Long> list = new ArrayList<>();
        for (Long allergyId : allergyList) { //7 3 1 10
            MemberAllergy memberAllergy = memberAllergyRepository.findByMemberIdAndAllergyId(memberId,allergyId);
//            list.add(memberAllergyRepository.findAllergyIdByMemberIdAndAllergyId(memberId, allergyId));
            list.add(memberAllergy.getAllergy().getId());
        }
        return list;
    }

}