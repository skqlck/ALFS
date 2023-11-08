package com.world.alfs.domain.member_allergy.repository;


import com.world.alfs.domain.member_allergy.MemberAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberAllergyRepository extends JpaRepository<MemberAllergy, Long> {

    List<MemberAllergy> findByMemberId(Long memberId);
    boolean existsByMemberIdAndAllergyId(Long memberId, Long allergyId);

}
