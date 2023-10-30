package com.world.alfs.controller.member_allergy;

import com.world.alfs.controller.member_allergy.request.AddMemberAllergyRequest;
import com.world.alfs.service.member_allergy.MemberAllergyService;
import com.world.alfs.service.member_allergy.dto.AddMemberAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member_allergy")
public class MemberAllergyController {
    private final MemberAllergyService memberAllergyService;

    @PostMapping()
    public Long addMemberAllergy(@RequestBody AddMemberAllergyRequest request){
        AddMemberAllergyDto dto = request.toDto();
        return memberAllergyService.addMemberAllergy(dto);
    }


}
