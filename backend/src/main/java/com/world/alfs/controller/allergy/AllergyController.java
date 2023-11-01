package com.world.alfs.controller.allergy;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.allergy.response.AllergyResponse;
import com.world.alfs.service.allergy.AllergyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/allergy")
@Slf4j
public class AllergyController {

    private final AllergyService allergyService;

    @GetMapping("/list")
    public ApiResponse<List<AllergyResponse>> getAllergyList() {
        List<AllergyResponse> response = allergyService.getAllergyList();
        return ApiResponse.ok(response);
    }

}
