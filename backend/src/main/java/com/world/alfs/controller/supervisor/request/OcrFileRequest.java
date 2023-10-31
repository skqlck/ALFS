package com.world.alfs.controller.supervisor.request;

import com.sun.istack.NotNull;
import com.world.alfs.service.supervisor.dto.OcrFileDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class OcrFileRequest {

    @NotNull
    private String format;

    @NotNull
    private String name;

    @Builder
    public OcrFileRequest(String format, String name) {
        this.format = format;
        this.name = name;
    }

    public OcrFileDto toDto(MultipartFile file){
        return OcrFileDto.builder()
                .format(this.format)
                .name(this.name)
                .file(file)
                .build();
    }
}
