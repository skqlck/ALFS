package com.world.alfs.domain.allergy.repository;

import com.world.alfs.domain.allergy.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findTop22ByOrderById();

    Optional<Allergy> findByAllergyName(String s);

    Optional<Allergy> findByAllergyNameAndAllergyType(String s, int isAllergy);

    Integer findAllergyTypeById(Long memberAllergyId);

    Optional<Allergy> findByIdAndAllergyType(Long allergyId, int isAllergy);

    List<Allergy> findByAllergyType(int isAllergy);

}
