package com.world.alfs.service.member;


import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.service.member.dto.AddMemberDto;
import com.world.alfs.service.member.dto.LoginMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository userRepository;

    // 회원가입
    public Long addMember(AddMemberDto addMemberDto) throws Exception{
        if (checkIdentifier(addMemberDto.getIdentifier())) throw new Exception("중복된 아이디 입니다.");
        if (checkEmail(addMemberDto.getEmail())) throw new Exception("중복된 이메일 입니다.");
        if (checkPhoneNumber(addMemberDto.getPhoneNumber())) throw new Exception("중복된 전화번호 입니다.");
        if (checkPassword(addMemberDto.getPassword(), addMemberDto.getPasswordCheck())) throw new Exception("비밀번호가 서로 다릅니다.");
        Member member = userRepository.save(addMemberDto.toEntity());
        return member.getId();
    }
    // 로그인
    public Optional<Long> login(LoginMemberDto loginMemberDto) {
        Optional<Member> member = userRepository.findByIdentifierAndPassword(loginMemberDto.getIdentifier(), loginMemberDto.getPassword());
        if (member.isPresent()){
            return Optional.of(member.get().getId());
        }
        return Optional.of(0L);
    }

    // 로그아웃

    // Identifier 중복 확인
    public boolean checkIdentifier(String identifier){
        Optional<Member> result = userRepository.findByIdentifier(identifier);
        if (result.isPresent()) return true;
        return false;
    }
    // Email 확인
    public boolean checkEmail(String email){
        Optional<Member> result = userRepository.findByEmail(email);
        if (result.isPresent()) return true;
        return false;
    }

    // Phone Number 확인
    public boolean checkPhoneNumber(String phoneNumber){
        Optional<Member> result = userRepository.findByPhoneNumber(phoneNumber);
        if (result.isPresent()) return true;
        return false;
    }

    // Password 일치 확인
    public boolean checkPassword(String password, String passwordCheck){
        return !Objects.equals(password, passwordCheck);
    }

    // 패턴 일치 확인
    private boolean checkPattern(String target, String pattern){
        return Pattern.matches(pattern, target);
    }
}