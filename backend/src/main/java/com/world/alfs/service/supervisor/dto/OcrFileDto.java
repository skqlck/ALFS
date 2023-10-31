package com.world.alfs.service.supervisor.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OcrFileDto {

    @NotNull
    private String format;

    @NotNull
    private String name;

    @NotNull
    private MultipartFile file;

    @Builder
    public OcrFileDto(String format, String name, MultipartFile file) {
        this.format = format;
        this.name = name;
        this.file = file;
    }

    @Override
    public String toString() {
        return "OcrFileDto{" +
                "format='" + format + '\'' +
                ", name='" + name + '\'' +
                ", file=" + file +
                '}';
    }
}
