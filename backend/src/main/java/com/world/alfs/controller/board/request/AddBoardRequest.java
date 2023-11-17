package com.world.alfs.controller.board.request;

import com.world.alfs.service.board.dto.AddBoardDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBoardRequest {
    private Long member_id;
    private AddBoardDto board;

    @Builder
    public AddBoardRequest(Long member_id, AddBoardDto board) {
        this.member_id = member_id;
        this.board = board;
    }
}
