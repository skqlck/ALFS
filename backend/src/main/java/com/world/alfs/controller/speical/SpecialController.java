package com.world.alfs.controller.speical;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.speical.request.AddSpecialReqeust;
import com.world.alfs.controller.speical.response.GetSpecialResponse;
import com.world.alfs.service.speical.SpecialService;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/special")
@Slf4j
public class SpecialController {

    private final SpecialService specialService;

    @PostMapping()
    public ApiResponse<Long> addSpecial(@RequestBody AddSpecialReqeust request) throws Exception {
        AddSpecialDto dto = request.toDto();
        Long id = specialService.addSpecial(dto);
        return ApiResponse.ok(id);
    }

    @GetMapping("/all")
    public ApiResponse<List<GetSpecialResponse>> getAllSpecial(){
        List<GetSpecialResponse> specialList = specialService.getAllSpecial();
        return ApiResponse.ok(specialList);
    }


    @GetMapping("/{id}")
    public ApiResponse<GetSpecialResponse> getSpecial(@PathVariable Long id){
        GetSpecialResponse saveSpecial =  specialService.getSpecial(id);
        return ApiResponse.ok(saveSpecial);
    }

    @PatchMapping("/{id}")
    public ApiResponse<Long> setSpecial(@PathVariable Long id, @RequestBody AddSpecialReqeust request){
        AddSpecialDto dto = request.toDto();
        Long saveId = specialService.setSpecial(id, dto);
        return ApiResponse.ok(saveId);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Long> deleteSpecial(@PathVariable Long id){
        Long deleteId = specialService.deleteSpecial(id);
        return ApiResponse.ok(deleteId);
    }


}
