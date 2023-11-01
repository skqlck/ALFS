package com.world.alfs.controller.supervisor.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupervisorLoginRequest {
    @NotNull
    private String identifier;

    @NotNull
    private String password;


}
