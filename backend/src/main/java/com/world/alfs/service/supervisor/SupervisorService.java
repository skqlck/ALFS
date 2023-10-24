package com.world.alfs.service.supervisor;
;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;


}