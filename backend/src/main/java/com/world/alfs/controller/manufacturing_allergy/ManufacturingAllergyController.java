package com.world.alfs.controller.manufacturing_allergy;

import com.world.alfs.controller.manufacturing_allergy.request.AddManuAllergyRequest;
import com.world.alfs.controller.manufacturing_allergy.request.GetManuAllergyRequest;
import com.world.alfs.service.manufacturing_allergy.ManufacturingAllergyService;
import com.world.alfs.service.manufacturing_allergy.dto.AddManuAllergyDto;
import com.world.alfs.service.manufacturing_allergy.dto.GetManuAllergyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/add")
    public Long addManuAllergy(@RequestBody AddManuAllergyRequest request) {
        List<AddManuAllergyDto> dtoList = request.toDto();
        Long productId = manufacturingAllergyService.addManuAllergy(request.getProductId(), dtoList);

        return productId;
    }

}
