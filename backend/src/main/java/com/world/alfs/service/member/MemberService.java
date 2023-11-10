package com.world.alfs.service.member;


import com.world.alfs.controller.ApiResponse;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.service.member.dto.AddMemberDto;
import com.world.alfs.service.member.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse getMember(Long member_id) {
        Member member = userRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return ApiResponse.ok(member.toGetMemberResponse());
    }

    // 회원가입
    public Long addMember(AddMemberDto addMemberDto) throws Exception{
        if (checkIdentifier(addMemberDto.getIdentifier())) throw new Exception("중복된 아이디 입니다.");
        if (checkEmail(addMemberDto.getEmail())) throw new Exception("중복된 이메일 입니다.");
        if (checkPhoneNumber(addMemberDto.getPhoneNumber())) throw new Exception("중복된 전화번호 입니다.");
        if (checkPassword(addMemberDto.getPassword(), addMemberDto.getPasswordCheck())) throw new Exception("비밀번호가 서로 다릅니다.");
        Member member = userRepository.save(addMemberDto.toEntity(passwordEncoder));
        return member.getId();
    }
    // 로그인
    public Long login(LoginMemberDto loginMemberDto) {
        Member member = userRepository.findByIdentifierAndActivate(loginMemberDto.getIdentifier(), true).stream()
                .filter(m -> passwordEncoder.matches(loginMemberDto.getPassword(), m.getPassword())).findAny()
                .orElseThrow(()->new IllegalArgumentException("아이디 혹은 비밀번호가 틀렸습니다."));
        return member.getId();
    }

    // 로그아웃

    // Identifier 중복 확인
    public boolean checkIdentifier(String identifier){
        return userRepository.findByIdentifierAndActivate(identifier, true).isPresent();
    }
    // Email 확인
    public boolean checkEmail(String email){
        return userRepository.findByEmailAndActivate(email, true).isPresent();
    }

    // Phone Number 확인
    public boolean checkPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumberAndActivate(phoneNumber, true).isPresent();
    }

    // Password 일치 확인
    public boolean checkPassword(String password, String passwordCheck){
        return !Objects.equals(password, passwordCheck);
    }

    // 패턴 일치 확인
    private boolean checkPattern(String target, String pattern){
        return Pattern.matches(pattern, target);
    }

    // 회원정보 수정
    public ApiResponse updateMember(Long member_id, AddMemberDto addMemberDto){
        Member member = userRepository.findById(member_id).stream()
                .filter(m -> m.getActivate()).findAny()
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        if (!member.getIdentifier().equals(addMemberDto.getIdentifier()) && checkIdentifier(addMemberDto.getIdentifier())) throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        if (!member.getEmail().equals(addMemberDto.getEmail()) && checkEmail(addMemberDto.getEmail())) throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        if (!member.getPhoneNumber().equals(addMemberDto.getPhoneNumber()) && checkPhoneNumber(addMemberDto.getPhoneNumber())) throw new IllegalArgumentException("이미 존재하는 전화번호 입니다.");
        if (checkPassword(addMemberDto.getPassword(), addMemberDto.getPasswordCheck())) throw new IllegalArgumentException(("비밀번호가 서로 다릅니다."));

        member.setIdentifier(addMemberDto.getIdentifier());
        member.setEmail(addMemberDto.getEmail());
        member.setPhoneNumber(addMemberDto.getPhoneNumber());
        member.setPassword(passwordEncoder.encode(addMemberDto.getPassword()));
        member.setBirth(addMemberDto.getBirth());
        member.setName(addMemberDto.getName());
        return ApiResponse.ok(userRepository.save(member).toGetMemberResponse());
    }

    // 회원 탈퇴
    public ApiResponse deactivateMember(Long member_id, String password){
        Member member = userRepository.findById(member_id).stream()
                .filter(m -> m.getActivate())
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        if (! passwordEncoder.matches(password, member.getPassword())) throw new IllegalArgumentException("비밀번호가 틀렷습니다.");
        member.setActivate(false);
        userRepository.save(member);
        return ApiResponse.ok("삭제되었습니다.");
    }
}