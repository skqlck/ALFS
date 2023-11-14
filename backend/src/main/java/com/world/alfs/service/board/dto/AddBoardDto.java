package com.world.alfs.service.board.dto;

import com.world.alfs.domain.board.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBoardDto {
    private String title;
    private String content;

    @Builder
    public AddBoardDto(String title, String content, String comment) {
        this.title = title;
        this.content = content;
    }

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
