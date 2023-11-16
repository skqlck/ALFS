package com.world.alfs.controller.board;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.board.request.AddBoardRequest;
import com.world.alfs.controller.board.request.AddCommentRequest;
import com.world.alfs.controller.board.request.UpdateBoardRequest;
import com.world.alfs.controller.board.response.BoardResponse;
import com.world.alfs.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{member_id}")
    public ApiResponse getAllBoard(@PathVariable Long member_id){
        return boardService.getAllBoard(member_id);
    }

    @GetMapping("/{member_id}/{board_id}")
    public ApiResponse getBoardDetail(@PathVariable Long member_id, @PathVariable Long board_id){
        return boardService.getBoardDetail(member_id, board_id);
    }

    @GetMapping("/list")
    public ApiResponse<List<BoardResponse>> getBoardList(){
        return boardService.getBoardList();
    }

    @PostMapping("/add")
    public ApiResponse addBoard(@RequestBody AddBoardRequest addBoardRequest){
        return boardService.addBoard(addBoardRequest.getMember_id(), addBoardRequest.getBoard());
    }

    @PostMapping("/supervisor")
    public ApiResponse<Long> addComment(@RequestBody AddCommentRequest request){
        return boardService.addComment(request.toDto());
    }

    @GetMapping("/supervisor/{board_id}")
    public ApiResponse<Long> getBoardDetailSupervisor(@PathVariable Long board_id){
        return boardService.getBoardDetailSupervisor(board_id);
    }

    @PutMapping("/update")
    public ApiResponse updatedBoard(@RequestBody UpdateBoardRequest updateBoardRequest){
        return boardService.updateBoard(updateBoardRequest.getMember_id(), updateBoardRequest.getBoard());
    }
}
