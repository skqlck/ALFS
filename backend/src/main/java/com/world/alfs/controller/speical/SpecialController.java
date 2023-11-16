package com.world.alfs.controller.speical;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.speical.request.AddSpecialQueueRequest;
import com.world.alfs.controller.speical.request.AddSpecialReqeust;
import com.world.alfs.controller.speical.request.DeleteSpecialReqeust;
import com.world.alfs.controller.speical.request.SetSpecialReqeust;
import com.world.alfs.controller.speical.response.GetSpecialListResponse;
import com.world.alfs.controller.speical.response.GetSpecialResponse;
import com.world.alfs.service.speical.SpecialService;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import com.world.alfs.service.speical.dto.AddSpecialQueueDto;
import com.world.alfs.service.speical.dto.DeleteSpecialDto;
import com.world.alfs.service.speical.dto.SetSpecialDto;
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
    public ApiResponse<Long> addSpecial(@RequestBody AddSpecialReqeust request) {
        AddSpecialDto dto = request.toDto();
        Long id = specialService.addSpecial(dto);
        return ApiResponse.ok(id);
    }

    @GetMapping("/all")
    public ApiResponse<List<GetSpecialListResponse>> getAllSpecial(){
        List<GetSpecialListResponse> specialList = specialService.getAllSpecial();
        return ApiResponse.ok(specialList);
    }

    @GetMapping("/all/{memberId}")
    public ApiResponse<List<GetSpecialListResponse>> getAllSpecial(@PathVariable Long memberId){
        List<GetSpecialListResponse> specialMemberList = specialService.getAllSpecial(memberId);
        return ApiResponse.ok(specialMemberList);
    }


    @GetMapping("/{id}")
    public ApiResponse<GetSpecialResponse> getSpecial(@PathVariable Long id){
        GetSpecialResponse saveSpecial =  specialService.getSpecial(id);
        return ApiResponse.ok(saveSpecial);
    }

    @GetMapping("/{memberId}/{id}")
    public ApiResponse<GetSpecialResponse> getSpecial(@PathVariable Long memberId, @PathVariable Long id){
        GetSpecialResponse saveSpecial =  specialService.getMemberSpecial(memberId, id);
        return ApiResponse.ok(saveSpecial);
    }

    @PatchMapping("/{id}")
    public ApiResponse<Long> setSpecial(@PathVariable Long id, @RequestBody SetSpecialReqeust request){
        SetSpecialDto dto = request.toDto();
        Long saveId = specialService.setSpecial(id, dto);
        return ApiResponse.ok(saveId);
    }

    @PostMapping("/{id}")
    public ApiResponse<Long> deleteSpecial(@PathVariable Long id, @RequestBody DeleteSpecialReqeust request){
        DeleteSpecialDto dto = request.toDto();
        Long deleteId = specialService.deleteSpecial(id, dto);
        return ApiResponse.ok(deleteId);
    }

    @PostMapping("/queue")
    public ApiResponse<Long> addQueue(@RequestBody AddSpecialQueueRequest request) {
        AddSpecialQueueDto dto = request.toDto();
        log.debug("dto.getProductId: {}, dto.getMemberId: {}", dto.getProductId(), dto.getMemberId());
        specialService.addQueue(dto);
        return ApiResponse.ok(request.getMemberId());
    }

    @GetMapping("/queue/{productId}/{memberId}")
    public ApiResponse<Long> getWaitingOrder(@PathVariable Long productId, @PathVariable Long memberId) {
        specialService.getWaitingOrder(productId, memberId);
        return ApiResponse.ok(memberId);
    }

    @GetMapping("/queue/{productId}/{startRank}/{endRank}")
    public void addCart(@PathVariable Long productId, @PathVariable Long startRank, @PathVariable Long endRank) {
        specialService.addCart(productId, startRank, endRank);
    }

    @GetMapping("/register/{productId}/{cnt}")
    public void addSpecialCnt(@PathVariable Long productId, @PathVariable String cnt) {
        specialService.addSpecialCnt(productId, cnt);
    }

}
