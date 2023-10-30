package com.world.alfs.controller.member;
import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.member.request.AddMemberRequest;
import com.world.alfs.controller.product.request.AddProductRequest;
import com.world.alfs.service.member.MemberService;
import com.world.alfs.service.member.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ApiResponse<Long> addMember(@RequestBody AddMemberRequest addMemberRequest){
        AddMemberDto addMemberDto = addMemberRequest.toDto();
        return ApiResponse.ok(memberService.addMember(addMemberDto));
    }
}
