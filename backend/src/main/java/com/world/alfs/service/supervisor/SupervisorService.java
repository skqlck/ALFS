package com.world.alfs.service.supervisor;
;
import com.world.alfs.controller.supervisor.response.SupervisorLoginResponse;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;

    public SupervisorLoginResponse loginSupervisor(String supervisorIdentifier, String supervisorPassword) {

        Optional<Supervisor> supervisor = Optional.ofNullable(supervisorRepository.findByIdentifierAndPassword(supervisorIdentifier, supervisorPassword));
        SupervisorLoginResponse supervisorLoginResponse = null;

        if(supervisor.isPresent()) {
            supervisorLoginResponse = SupervisorLoginResponse.builder()
                    .identifier(supervisor.get().getIdentifier())
                    .build();
            return supervisorLoginResponse;
        }else{
            // 예외 처리 : 유저를 찾을 수 없는 경우
            throw new NoSuchElementException("아이디와 비밀번호를 확인하세요");
        }
    }
}