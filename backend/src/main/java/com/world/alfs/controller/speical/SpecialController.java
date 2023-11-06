package com.world.alfs.controller.speical;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.speical.request.AddSpecialReqeust;
import com.world.alfs.service.speical.SpecialService;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/all")
//    public ApiResponse<List<GetSpecialListResponse>> getAllProduct(){
//        List<GetSpecialListResponse> specialList = specialService.getAllProduct();
//        return ApiResponse.ok(specialList);
//    }
//
//
//    @GetMapping("/{id}")
//    public ApiResponse<SpecialResponse> getProduct(@PathVariable Long id){
//        Optional<SpecialResponse> saveSpecial =  specialService.getProduct(id);
//        return ApiResponse.ok(saveSpecial);
//    }
//
//    @PatchMapping("/{id}")
//    public ApiResponse<Long> setProduct(@PathVariable Long id, @RequestBody AddSpecialReqeust request){
//        AddSpecialDto dto = request.toDto();
//        Long saveId = specialService.setProduct(id, dto);
//        return ApiResponse.ok(saveId);
//    }
//
//    @DeleteMapping("/{id}")
//    public ApiResponse<Long> deleteProduct(@PathVariable Long id){
//        Long deleteId = specialService.deleteProduct(id);
//        return ApiResponse.ok(deleteId);
//    }


}
