package com.world.alfs.controller.board.request;

import com.world.alfs.service.board.AddCommentDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCommentRequest {
    private Long id;
    private String comment;
    @Builder
    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }


    public AddCommentDto toDto() {
        return AddCommentDto.builder()
                .comment(this.comment)
                .id(id)
                .build();
    }
}
