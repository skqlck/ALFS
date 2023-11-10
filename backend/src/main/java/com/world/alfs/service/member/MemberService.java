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

    private String EMAIL_REGEXP = "^[a-zA-Z0-9+-.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private String IDENTIFIER_REGEXP = "^[a-zA-Z0-9]{6,16}$";
    private String PHONENUMBER_REGEXP = "^010\\d{3,4}\\d{4}$";
    private String BIRTH_REGEXP = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private String NAME_REGEXP = "^[가-힣]{2,6}$";
    private String PASSWORD_REGEXP = "^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,15}$";

    public ApiResponse getMember(Long member_id) {
        Member member = userRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return ApiResponse.ok(member.toGetMemberResponse());
    }

    // 회원가입
    public Long addMember(AddMemberDto addMemberDto) throws Exception{
        validateAll(addMemberDto);
        isExistAll(addMemberDto);
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

    // TODO : 로그아웃

    // Identifier 중복 확인
    public boolean isIdentifierExist(String identifier){
        return userRepository.findByIdentifierAndActivate(identifier, true).isPresent();
    }
    // Email 중복 확인
    public boolean isEmailExist(String email){
        return userRepository.findByEmailAndActivate(email, true).isPresent();
    }

    // Phone Number 중복 확인
    public boolean isPhoneNumberExist(String phoneNumber){
        return userRepository.findByPhoneNumberAndActivate(phoneNumber, true).isPresent();
    }

    // Password 일치 확인
    public boolean checkPassword(String password, String passwordCheck){
        return !Objects.equals(password, passwordCheck);
    }

    // 정규표현식 검사
    public boolean checkPattern(String regexp,String string){
        return Pattern.matches(regexp, string);
    }

    // 회원정보 수정
    public ApiResponse updateMember(Long member_id, AddMemberDto addMemberDto) throws Exception{
        Member member = userRepository.findById(member_id).stream()
                .filter(m -> m.getActivate()).findAny()
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));

        validateAll(addMemberDto);
        if (!member.getIdentifier().equals(addMemberDto.getIdentifier()) && isIdentifierExist(addMemberDto.getIdentifier())) throw new Exception("이미 존재하는 아이디 입니다.");
        if (!member.getEmail().equals(addMemberDto.getEmail()) && isEmailExist(addMemberDto.getEmail())) throw new Exception("이미 존재하는 이메일 입니다.");
        if (!member.getPhoneNumber().equals(addMemberDto.getPhoneNumber()) &&isPhoneNumberExist(addMemberDto.getPhoneNumber())) throw new Exception("이미 존재하는 전화번호 입니다.");

        member.setIdentifier(addMemberDto.getIdentifier());
        member.setEmail(addMemberDto.getEmail());
        member.setPhoneNumber(addMemberDto.getPhoneNumber());
        member.setPassword(passwordEncoder.encode(addMemberDto.getPassword()));
        member.setBirth(addMemberDto.getBirth());
        member.setName(addMemberDto.getName());
        return ApiResponse.ok(userRepository.save(member).toGetMemberResponse());
    }

    public boolean isExistAll(AddMemberDto addMemberDto) throws Exception{
        if (isIdentifierExist(addMemberDto.getIdentifier())) throw new Exception("이미 존재하는 아이디 입니다.");
        if (isEmailExist(addMemberDto.getEmail())) throw new Exception("이미 존재하는 이메일 입니다.");
        if (isPhoneNumberExist(addMemberDto.getPhoneNumber())) throw  new Exception("이미 존재하는 전화번호 입니다.");
        return false;
    }
    public boolean validateAll(AddMemberDto addMemberDto) throws Exception {
        if (!checkPattern(IDENTIFIER_REGEXP, addMemberDto.getIdentifier())) throw new Exception("아이디 형식에 맞지 않습니다.");
        if (!checkPattern(EMAIL_REGEXP, addMemberDto.getEmail())) throw new Exception("이메일 형식에 맞지 않습니다.");
        if (!checkPattern(PHONENUMBER_REGEXP, addMemberDto.getPhoneNumber())) throw new Exception("전화번호 형식에 맞지 않습니다.");
        if (!checkPattern(BIRTH_REGEXP, addMemberDto.getBirth())) throw new Exception("생년월일 형식에 맞지 않습니다.");
        if (!checkPattern(NAME_REGEXP, addMemberDto.getName())) throw new Exception("아이디 형식에 맞지 않습니다.");
        if (!checkPattern(PASSWORD_REGEXP, addMemberDto.getPassword())) throw new Exception("비밀번호 형식에 맞지 않습니다.");
        if (checkPassword(addMemberDto.getPassword(), addMemberDto.getPasswordCheck())) throw new Exception("비밀번호가 서로 다릅니다.");
        return false;
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