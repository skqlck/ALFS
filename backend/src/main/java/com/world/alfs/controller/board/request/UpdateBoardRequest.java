package com.world.alfs.controller.board.request;

import com.world.alfs.service.board.dto.UpdateBoardDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBoardRequest {
    private Long member_id;
    private UpdateBoardDto board;

    @Builder
    public UpdateBoardRequest(Long member_id, UpdateBoardDto board) {
        this.member_id = member_id;
        this.board = board;
    }
}
