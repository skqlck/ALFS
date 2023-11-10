package com.world.alfs.controller.member.request;

import com.world.alfs.service.member.dto.AddMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMemberRequest {
    private Long member_id;
    private AddMemberDto member;

    @Builder
    public UpdateMemberRequest(Long member_id, AddMemberDto member) {
        this.member_id = member_id;
        this.member = member;
    }
}
