package com.world.alfs.controller.allergy;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.controller.member_allergy.request.AddMemberAllergyRequest;
import com.world.alfs.service.allergy.AllergyService;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/check")
    public ApiResponse<List<AddMemberAllergyDto>> checkAllergyName(){
        //@PathVariable List<String> allergyNameList
        //@PathVariable 로 member_id 추가해야함
        List<String> allergyNameList = List.of(new String[]{"토마토", "대두"});
        List<AddMemberAllergyDto> dto = allergyService.checkAllergyName(2L, allergyNameList,1);
        //dto를 request body로 같은 서버 내의 다른 프로젝트인 /api/member_allergy [POST 요청]불러오기
        System.out.println("여기는 AllergyController "+dto.get(0).getMember_id());
        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청 엔티티 생성
        HttpEntity<List<AddMemberAllergyDto>> requestEntity = new HttpEntity<>(dto, headers);
        System.out.println(requestEntity.getBody());

        // HTTP POST 요청 보내기
        ResponseEntity<ApiResponse> response = restTemplate.postForEntity("http://localhost:8080/api/member_allergy",requestEntity, ApiResponse.class);

        //요청 응답 처리 부분
        if (response.getStatusCode().is2xxSuccessful()) {
            return ApiResponse.ok(dto);
        }else{
            return ApiResponse.badRequest("회원 알러지 등록에 실패했습니다.");
        }
    }

    @GetMapping("/hate")
    public ApiResponse<Boolean> checkHateName(@PathVariable Long member_id,@PathVariable List<String> hateNameList){
        List<AddMemberAllergyDto> dto = allergyService.checkAllergyName(member_id, hateNameList,0);
        return ApiResponse.ok(true);
    }


}
