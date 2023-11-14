package com.world.alfs.service.board.dto;

import com.world.alfs.domain.board.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetBoardListDto {
    private Long board_id;
    private String title;
    private Boolean status;

    @Builder
    public GetBoardListDto(Long board_id, String title, boolean status) {
        this.board_id = board_id;
        this.title = title;
        this.status = status;
    }
}
