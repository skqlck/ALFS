package com.world.alfs.domain.member.repository;

import com.world.alfs.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByIdentifierAndActivate(String identifier, boolean activate);
    Optional<Member> findByEmailAndActivate(String Email, boolean activate);
    Optional<Member> findByPhoneNumberAndActivate(String phoneNumber, boolean activate);
}
