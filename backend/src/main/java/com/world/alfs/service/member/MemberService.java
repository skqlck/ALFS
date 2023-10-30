package com.world.alfs.service.member;


import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.service.member.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository userRepository;

    public Long addMember(AddMemberDto addMemberDto) {
        Member member = userRepository.save(addMemberDto.toEntity());
        return member.getId();
    }
}