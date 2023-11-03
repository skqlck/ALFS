package com.world.alfs.controller.manufacturing_allergy;

import com.world.alfs.controller.manufacturing_allergy.request.GetManuAllergyRequest;
import com.world.alfs.service.manufacturing_allergy.ManufacturingAllergyService;
import com.world.alfs.service.manufacturing_allergy.dto.GetManuAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturing-allergy")
@Slf4j
public class ManufacturingAllergyController {

    private final ManufacturingAllergyService manufacturingAllergyService;

    @PostMapping
    public boolean getManuAllergy(@RequestBody GetManuAllergyRequest request) {
        GetManuAllergyDto dto = request.toDto();
        boolean isManuAllergy = manufacturingAllergyService.getManuAllergy(dto);

        return isManuAllergy;
    }

}
