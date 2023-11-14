package com.world.alfs.service.board;


import com.world.alfs.common.exception.CustomException;
import com.world.alfs.common.exception.ErrorCode;
import com.world.alfs.controller.ApiResponse;
import com.world.alfs.domain.board.Board;
import com.world.alfs.domain.board.repository.BoardRepository;
import com.world.alfs.service.board.dto.AddBoardDto;
import com.world.alfs.service.board.dto.GetBoardListDto;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.service.board.dto.UpdateBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public ApiResponse getAllBoard(Long member_id){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        List<GetBoardListDto> boardList = boardRepository.findByMember(member)
                .stream().map(board -> board.toGetBoardListDto())
                .collect(Collectors.toList());
        return ApiResponse.ok(boardList);
    }

    public ApiResponse getBoardDetail(Long member_id, Long board_id){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Board board = boardRepository.findByMember(member).stream()
                .filter(board1 -> board1.getId() == board_id).findAny()
                .orElseThrow(()-> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        return ApiResponse.ok(board.toGetBoardDetailDto());
    }

    public ApiResponse addBoard(Long member_id, AddBoardDto addBoardDto){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Board board = addBoardDto.toEntity();
        board.setMember(member);
        board.setComment(null);
        Board newBoard = boardRepository.save(board);
        return ApiResponse.created("생성되었습니다.",newBoard.toGetBoardDetailDto());
    }

    public ApiResponse updateBoard(Long member_id, UpdateBoardDto updateBoardDto){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Board board = boardRepository.findByMember(member).stream()
                .filter(b -> b.getId().equals(updateBoardDto.getBoard_id())).findAny()
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        if ( board.getComment() != null ) throw new CustomException(ErrorCode.CANNOT_CHANGE_BOARD);
        board.setTitle(updateBoardDto.getTitle());
        board.setContent(updateBoardDto.getContent());
        Board updatedBoard = boardRepository.save(board);
        return ApiResponse.ok(updatedBoard.toGetBoardDetailDto());
    }
}