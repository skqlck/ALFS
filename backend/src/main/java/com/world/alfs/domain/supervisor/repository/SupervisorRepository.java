package com.world.alfs.domain.supervisor.repository;

import com.world.alfs.domain.supervisor.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

}
