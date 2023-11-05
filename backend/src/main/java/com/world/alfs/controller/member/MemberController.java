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
        if (addressService.addAddress(addressDto, member_id, true).isPresent()){
            return ApiResponse.created("회원가입이 완료되었습니다. 주소지 설정이 완료되었습니다.", null);
        }
        return ApiResponse.created("회원가입이 완료되었습니다. 주소지 설정이 실패하였습니다.", null);
    }

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestBody LoginRequest loginRequest){
        Optional<Long> member_id = memberService.login(loginRequest.toDto());
        if (member_id.get() == 0){
            return ApiResponse.badRequest("잘못된 아이디 혹은 비밀번호 입니다.");
        }
        return ApiResponse.ok(member_id.get());
    }

    @PostMapping("/logout")
    public ApiResponse<Long> logout(@RequestBody LogoutRequest logoutRequest){
        return ApiResponse.ok(null);
    }

    @GetMapping("/check/identifier/{identifier}")
    public ApiResponse<Boolean> checkIdentifier(@PathVariable("identifier") String identifier){
        return ApiResponse.ok(memberService.checkIdentifier(identifier));
    }

    @GetMapping("/check/email/{email}")
    public ApiResponse<Boolean> checkEmail(@PathVariable("email") String email){
        return ApiResponse.ok(memberService.checkEmail(email));
    }

    @GetMapping("/check/phoneNumber/{phoneNumber}")
    public ApiResponse<Boolean> checkPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        return ApiResponse.ok(memberService.checkPhoneNumber(phoneNumber));
    }
}
