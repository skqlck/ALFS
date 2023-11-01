package com.world.alfs.service.supervisor.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class OcrUrlDto {

    // 속성
    @NotNull
    private String format;

    @NotNull
    private String url;

    @NotNull
    private String name;

    // builder
    @Builder
    public OcrUrlDto(String format, String url, String name) {
        this.format = format;
        this.url = url;
        this.name = name;
    }

}
