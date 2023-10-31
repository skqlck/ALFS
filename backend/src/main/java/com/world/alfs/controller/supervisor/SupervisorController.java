package com.world.alfs.controller.supervisor;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.supervisor.request.OcrFileRequest;
import com.world.alfs.controller.supervisor.request.OcrUrlRequest;
import com.world.alfs.controller.supervisor.request.SupervisorLoginRequest;
import com.world.alfs.controller.supervisor.response.FileIngredientResponse;
import com.world.alfs.controller.supervisor.response.SupervisorLoginResponse;
import com.world.alfs.service.aws_s3.AwsS3Service;
import com.world.alfs.service.supervisor.SupervisorService;
import com.world.alfs.service.supervisor.dto.OcrFileDto;
import com.world.alfs.service.supervisor.dto.OcrUrlDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisor")
@Slf4j
public class SupervisorController {

    private final SupervisorService supervisorService;
    private final AwsS3Service awsS3Service;

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

    @PostMapping("/ocr/file")
    public ApiResponse<FileIngredientResponse> getFileIngredient(@RequestPart("images") List<MultipartFile> multipartFile, @RequestPart("OcrFileRequest") OcrFileRequest request){

        List<String> imgUrls = new ArrayList<>();
        for(MultipartFile file : multipartFile){
            String imgUrl = null;
            try {
                imgUrl = awsS3Service.uploadFiles(file, "static");
                if(imgUrl == null || imgUrl.isEmpty()){
                    return ApiResponse.badRequest("이미지가 없습니다.");
                }
                imgUrls.add(imgUrl);
            } catch (Exception e) {
                log.debug("updateUserImage exception msg", e);
                return ApiResponse.badRequest("s3에 이미지 업로드룰 실패했습니다.");
            }
        }

        OcrFileDto dto = request.toDto(multipartFile.get(multipartFile.size()-1));
        List<String> response = supervisorService.getFileIngredient(dto);
        if(response.isEmpty()){
            log.debug("인식된 원재료명이 없습니다.");
        }

        FileIngredientResponse fileIngredientResponse = new FileIngredientResponse(imgUrls, response);
        return ApiResponse.ok(fileIngredientResponse);

    }
}
