package com.world.alfs.controller.allergy;

import com.world.alfs.service.allergy.AllergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/allergy")
@Slf4j
public class AllergyController {

    private final AllergyService allergyService;

}
