package com.world.alfs.domain.member_allergy.repository;


import com.world.alfs.domain.member_allergy.MemberAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAllergyRepository extends JpaRepository<MemberAllergy, Long> {

}
