package com.world.alfs.domain.allergy.repository;

import com.world.alfs.domain.allergy.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
