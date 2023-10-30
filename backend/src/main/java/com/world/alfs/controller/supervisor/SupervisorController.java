package com.world.alfs.controller.supervisor;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.supervisor.request.OcrUrlRequest;
import com.world.alfs.controller.supervisor.request.SupervisorLoginRequest;
import com.world.alfs.controller.supervisor.response.SupervisorLoginResponse;
import com.world.alfs.service.supervisor.SupervisorService;
import com.world.alfs.service.supervisor.dto.OcrUrlDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisor")
@Slf4j
public class SupervisorController {

    private final SupervisorService supervisorService;

    @PostMapping("/login")
    public ApiResponse<SupervisorLoginResponse> login(@RequestBody SupervisorLoginRequest request){
        String supervisorIdentifier = request.getIdentifier();
        String supervisorPassword = request.getPassword();

        SupervisorLoginResponse response = supervisorService.loginSupervisor(supervisorIdentifier, supervisorPassword);
        return ApiResponse.ok(response);
    }

    @PostMapping("/ocr/url")
    public ApiResponse<List<String>> getUrlIngredient(@RequestBody OcrUrlRequest request){

        OcrUrlDto dto = request.toDto();
        List<String> response = supervisorService.getUrlIngredient(dto);
        if(response.isEmpty()){
            return ApiResponse.badRequest("인식된 원재료명이 없습니다.");
        }
        return ApiResponse.ok(response);
    }
}
