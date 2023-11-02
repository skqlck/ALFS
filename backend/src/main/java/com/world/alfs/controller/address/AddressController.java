package com.world.alfs.controller.address;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.address.request.AddAddressRequest;
import com.world.alfs.controller.address.request.SetDefaultRequest;
import com.world.alfs.controller.address.response.GetAddressResponse;
import com.world.alfs.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{id}")
    public ApiResponse<List<GetAddressResponse>> getAddress(@PathVariable Long id){
        List<GetAddressResponse> address_list = addressService.getAddress(id);
        return ApiResponse.ok(address_list);
    }

    @PutMapping()
    public ApiResponse setDefaultAddress(@RequestBody SetDefaultRequest setDefaultRequest){
        Optional<GetAddressResponse> response = addressService.setAsDefaultAddress(setDefaultRequest.getMember_id(), setDefaultRequest.getAddress_id());
        if (response.isPresent()){
            return ApiResponse.ok(response.get());
        }
        return ApiResponse.badRequest("주소가 잘못되었습니다.");
    }

    @PostMapping()
    public ApiResponse addAddress(@RequestBody AddAddressRequest addAddressRequest){
        Optional<GetAddressResponse> address = addressService.addAddress(addAddressRequest.getAddress(), addAddressRequest.getMember_id(), false);
        if (address.isPresent()){
            return ApiResponse.created("주소가 추가되었습니다.", address.get());
        }
        return ApiResponse.badRequest("잘못된 정보입니다.");
    }
}
