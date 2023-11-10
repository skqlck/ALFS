package com.world.alfs.controller.member;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.member.request.*;
import com.world.alfs.service.address.AddressService;
import com.world.alfs.service.address.dto.AddressDto;
import com.world.alfs.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final AddressService addressService;

    @GetMapping("/{member_id}")
    public ApiResponse getMemberInfo(@PathVariable("member_id") Long member_id){
        try {
            return memberService.getMember(member_id);
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ApiResponse<Long> addMember(@RequestBody SignUpRequest signUpRequest){
        AddressDto addressDto = signUpRequest.getAddress();
        Long member_id;
        try {
            member_id = memberService.addMember(signUpRequest.getMember());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
        try {
            addressService.addAddress(addressDto, member_id, true);
            return ApiResponse.created("회원가입이 완료되었습니다. 주소지 설정이 완료되었습니다.", null);
        }
        catch (Exception e) {
            return ApiResponse.created("회원가입이 완료되었습니다. 주소지 설정에 실패하였습니다.", null);
        }
    }

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestBody LoginRequest loginRequest){
        try {
            return ApiResponse.ok(memberService.login(loginRequest.toDto()));
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ApiResponse<Long> logout(@RequestBody LogoutRequest logoutRequest){
        return ApiResponse.ok(null);
    }

    @GetMapping("/check/identifier/{identifier}")
    public ApiResponse<Boolean> checkIdentifier(@PathVariable("identifier") String identifier){
        return ApiResponse.ok(memberService.isIdentifierExist(identifier));
    }

    @GetMapping("/check/email/{email}")
    public ApiResponse<Boolean> checkEmail(@PathVariable("email") String email){
        return ApiResponse.ok(memberService.isEmailExist(email));
    }

    @GetMapping("/check/phoneNumber/{phoneNumber}")
    public ApiResponse<Boolean> checkPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        return ApiResponse.ok(memberService.isPhoneNumberExist(phoneNumber));
    }

    @PutMapping("/update")
    public ApiResponse updateMember(@RequestBody UpdateMemberRequest updateMemberRequest){
        try {
            return memberService.updateMember(updateMemberRequest.getMember_id(), updateMemberRequest.getMember());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }

    @PutMapping("/delete")
    public ApiResponse deactivateMember(@RequestBody DeactivateRequest deactivateRequest){
        try {
            return memberService.deactivateMember(deactivateRequest.getMember_id(), deactivateRequest.getPassword());
        }
        catch (Exception e){
            return ApiResponse.badRequest(e.getMessage());
        }
    }
}
