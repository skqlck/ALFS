package com.world.alfs.service.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBoardDto {
    private Long board_id;
    private String title;
    private String content;

    @Builder
    public UpdateBoardDto(Long board_id, String title, String content) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
    }
}
