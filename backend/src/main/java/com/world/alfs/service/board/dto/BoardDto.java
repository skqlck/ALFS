package com.world.alfs.service.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {
    private Long board_id;
    private String title;
    private String content;
    private String comment;

    @Builder
    public BoardDto(Long board_id, String title, String content, String comment) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.comment = comment;
    }
}
