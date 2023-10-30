package com.world.alfs.domain.allergy.repository;

import com.world.alfs.domain.allergy.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findTop22ByOrderById();

    Allergy findByAllergyName(String s);
}
