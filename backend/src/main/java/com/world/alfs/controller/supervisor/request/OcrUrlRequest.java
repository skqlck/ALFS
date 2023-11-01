package com.world.alfs.controller.supervisor.request;

import com.sun.istack.NotNull;
import com.world.alfs.service.supervisor.dto.OcrUrlDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OcrUrlRequest {

    @NotNull
    private String format;

    @NotNull
    private String url;

    @NotNull
    private String name;

    @Builder
    public OcrUrlRequest(String format, String url, String name) {
        this.format = format;
        this.url = url;
        this.name = name;
    }

    public OcrUrlDto toDto(){
        return OcrUrlDto.builder()
                .format(this.format)
                .url(this.url)
                .name(this.name)
                .build();
    }

}
