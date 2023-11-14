package com.world.alfs.domain.board.repository;

import com.world.alfs.domain.board.Board;
import com.world.alfs.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMember(Member member);
}
