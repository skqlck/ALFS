package com.world.alfs.domain.alternative.repository;

import com.world.alfs.domain.alternative.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
}
