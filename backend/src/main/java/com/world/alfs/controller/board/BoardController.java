package com.world.alfs.controller.board;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.board.request.AddBoardRequest;
import com.world.alfs.controller.board.request.UpdateBoardRequest;
import com.world.alfs.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ApiResponse addBoard(@RequestBody AddBoardRequest addBoardRequest){
        return boardService.addBoard(addBoardRequest.getMember_id(), addBoardRequest.getBoard());
    }

    @PutMapping("/update")
    public ApiResponse updatedBoard(@RequestBody UpdateBoardRequest updateBoardRequest){
        return boardService.updateBoard(updateBoardRequest.getMember_id(), updateBoardRequest.getBoard());
    }
}
