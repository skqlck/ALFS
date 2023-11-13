package com.world.alfs.domain.wining.repository;

import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.wining.Wining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WiningRepository extends JpaRepository<Wining,Long> {

    void deleteBySpecial(Special special);
}
