package com.world.alfs.domain.board;

import com.world.alfs.controller.board.response.BoardResponse;
import com.world.alfs.domain.member.Member;
import com.world.alfs.service.board.dto.GetBoardDetailDto;
import com.world.alfs.service.board.dto.GetBoardListDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board(Long id, String title, String content, String comment, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comment = comment;
        this.member = member;
    }

    // -- 비지니스 로직 --

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public GetBoardDetailDto toGetBoardDetailDto(){
        return GetBoardDetailDto.builder()
                .board_id(id)
                .title(title)
                .content(content)
                .comment(comment)
                .build();
    }

    public GetBoardListDto toGetBoardListDto(){
        return GetBoardListDto.builder()
                .board_id(id)
                .title(title)
                .status(comment != null)
                .build();
    }

    public BoardResponse toBoardResponse() {
        return BoardResponse.builder()
                .id(id)
                .title(title)
                .comment(comment)
                .content(content)
                .build();
    }
}
