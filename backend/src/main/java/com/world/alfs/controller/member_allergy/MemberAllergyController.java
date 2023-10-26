package com.world.alfs.controller.member_allergy;

import com.world.alfs.service.member_allergy.MemberAllergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member_allergy")
public class MemberAllergyController {
    private final MemberAllergyService memberAllergyService;
}
