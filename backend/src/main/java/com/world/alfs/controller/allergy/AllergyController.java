package com.world.alfs.controller.allergy;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.service.allergy.AllergyService;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/allergy")
@Slf4j
public class AllergyController {

    private final AllergyService allergyService;
    private final RestTemplate restTemplate;

    @GetMapping("/list")
    public ApiResponse<List<AllergyResponse>> getAllergyList() {
        List<AllergyResponse> response = allergyService.getAllergyList();
        return ApiResponse.ok(response);
    }

    @PostMapping("/check/{memberId}/{isAllergy}")
    public ApiResponse<List<AddMemberAllergyDto>> checkAllergyName(@RequestBody List<String> list, @PathVariable Long memberId, @PathVariable int isAllergy) {

        List<AddMemberAllergyDto> dto = allergyService.checkAllergyName(memberId, list, isAllergy);

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 엔티티 생성
        HttpEntity<List<AddMemberAllergyDto>> requestEntity = new HttpEntity<>(dto, headers);
        System.out.println(requestEntity.getBody());

        // HTTP POST 요청 보내기
        restTemplate.postForEntity("http://localhost:8080/api/member_allergy", requestEntity, ApiResponse.class);

        //요청 응답 처리 부분
        return ApiResponse.ok(dto);
    }

    @GetMapping("/hate")
    public ApiResponse<Boolean> checkHateName(@PathVariable Long member_id, @PathVariable List<String> hateNameList) {
        List<AddMemberAllergyDto> dto = allergyService.checkAllergyName(member_id, hateNameList, 0);
        return ApiResponse.ok(true);
    }


}
