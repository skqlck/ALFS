package com.world.alfs.domain.alternative.repository;

import com.world.alfs.domain.alternative.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlternativeRepository extends JpaRepository<Alternative, Long> {

    List<Alternative> findByParentIdIn(List<String> parentIds);

}
