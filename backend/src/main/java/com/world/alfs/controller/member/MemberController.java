package com.world.alfs.controller.member;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.member.request.*;
import com.world.alfs.service.address.AddressService;
import com.world.alfs.service.address.dto.AddressDto;
import com.world.alfs.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        List<String> allergy = signUpRequest.getAllergy();
        List<String> hate = signUpRequest.getHate();
        AddressDto addressDto = signUpRequest.getAddress();
        Long ret = memberService.addMember(signUpRequest.getMember());
        String message;

        if (ret > 0) {
            message = "회원가입에 성공했습니다.";
            if (addressService.addAddress(addressDto, ret, true).isPresent()){
                message = message + " 주소지 설정이 완료되었습니다.";
            }
            else {
                message = message + " 주소지가 설정되지 않았습니다.";
            }
            return ApiResponse.created(message, ret);
        }

        else if (ret == -1) {
            message = "중복된 아이디입니다.";
        }
        else if (ret == -2) {
            message = "중복된 이메일입니다.";
        }
        else if (ret == -3) {
            message = "중복된 전화번호입니다.";
        }
        else if (ret == -4) {
            message = "비밀번호가 서로 다릅니다.";
        }
        else {
            message = "알 수 없는 오류";
        }
        return ApiResponse.badRequest(message);
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
        Long id = logoutRequest.toDto().getId();
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
