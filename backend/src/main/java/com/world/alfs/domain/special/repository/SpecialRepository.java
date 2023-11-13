package com.world.alfs.domain.special.repository;

import com.world.alfs.domain.special.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpecialRepository extends JpaRepository<Special, Long> {

        List<Special> findByStart(LocalDateTime time);

        List<Special> findByEnd(LocalDateTime time);

    void deleteByProductId(Long id);

    Special findByProductId(Long id);
}
