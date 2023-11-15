package com.world.alfs.service.board;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class AddCommentDto {
    private Long id;
    private String comment;

    @Builder
    public AddCommentDto(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }
}
