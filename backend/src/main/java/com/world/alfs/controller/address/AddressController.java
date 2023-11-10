package com.world.alfs.controller.address;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.address.request.AddAddressRequest;
import com.world.alfs.controller.address.request.SetDefaultRequest;
import com.world.alfs.controller.address.request.UpdateAddressRequest;
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
        try {
            return ApiResponse.ok(addressService.getAddress(id));
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PutMapping()
    public ApiResponse setDefaultAddress(@RequestBody SetDefaultRequest setDefaultRequest){
        try {
            return addressService.setAsDefaultAddress(setDefaultRequest.getMember_id(), setDefaultRequest.getAddress_id());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PostMapping()
    public ApiResponse addAddress(@RequestBody AddAddressRequest addAddressRequest){
        try {
           return addressService.addAddress(addAddressRequest.getAddress(), addAddressRequest.getMember_id(), false);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ApiResponse deleteAddress(@RequestBody SetDefaultRequest setDefaultRequest){
        try {
            return addressService.deleteAddress(setDefaultRequest.getMember_id(), setDefaultRequest.getAddress_id());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ApiResponse updateAddress(@RequestBody UpdateAddressRequest updateAddressRequest){
        try {
            return addressService.updateAddress(updateAddressRequest.getMember_id(), updateAddressRequest.getAddress_id(), updateAddressRequest.getAddress());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
