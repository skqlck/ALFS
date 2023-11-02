package com.world.alfs.domain.address.repository;

import com.world.alfs.domain.address.Address;
import com.world.alfs.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByMember(Member member);
    List<Address> findByMemberAndStatus(Member member, Boolean status);
}
