package com.world.alfs.controller.member_allergy;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.service.member_allergy.MemberAllergyService;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member_allergy")
public class MemberAllergyController {
    private final MemberAllergyService memberAllergyService;
    @PostMapping()
    public void addMemberAllergy(@RequestBody List<AddMemberAllergyDto> request){
        System.out.println("redirect 성공하셨습니다: member_allergy");
        for(AddMemberAllergyDto dto : request){
            System.out.print(dto.getAllergy_id());
            memberAllergyService.addMemberAllergy(dto);
        }
    }

    @GetMapping("/{memberId}/{isAllergy}")
    public ApiResponse<List<AllergyResponse>> getMemberAllergy(@PathVariable Long memberId, @PathVariable int isAllergy) {
        List<AllergyResponse> allergyResponseList = memberAllergyService.getMemberAllergy(memberId, isAllergy);

        return ApiResponse.ok(allergyResponseList);
    }

}
