package com.world.alfs.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponse {
    private Long id;

    private String title;

    private String content;

    private String comment;

    @Builder
    public BoardResponse(Long id, String title, String content, String comment) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comment = comment;
    }
}
