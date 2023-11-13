package com.world.alfs.controller.speical.request;

import com.world.alfs.service.speical.dto.DeleteSpecialDto;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeleteSpecialReqeust {

    private Long supervisorId;

    public DeleteSpecialDto toDto() {
        return DeleteSpecialDto.builder()
                .supervisorId(this.supervisorId)
                .build();
    }
}
