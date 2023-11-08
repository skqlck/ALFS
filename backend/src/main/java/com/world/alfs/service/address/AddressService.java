package com.world.alfs.service.address;

import com.world.alfs.controller.ApiResponse;
import com.world.alfs.controller.address.response.GetAddressResponse;
import com.world.alfs.domain.address.Address;
import com.world.alfs.domain.address.repository.AddressRepository;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.member.repository.MemberRepository;
import com.world.alfs.service.address.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    // 주소지 추가
    public Optional<GetAddressResponse> addAddress(AddressDto addressDto, Long member_id, Boolean status){
        // TODO : 주소지 확인 로직
        Optional<Member> member = memberRepository.findById(member_id);
        if (member.isPresent()){
            if (addressRepository.findByMember(member.get()).size() > 4) return Optional.empty();
            addressDto.setMember(member.get());
            Address address = addressRepository.save(addressDto.toEntity(status));
            return Optional.of(address.toGetAddressResponse());
        }
        return Optional.empty();
    }

    // 주소지 조회
    public List<GetAddressResponse> getAddress(Long id){
        List<Address> addressList = addressRepository.findByMember(memberRepository.findById(id).get());
        List<GetAddressResponse> addressResponseList = new ArrayList<>();
        for (Address address : addressList){
            addressResponseList.add(address.toGetAddressResponse());
        }
        return addressResponseList;
    }

    // 기본 주소지로 설정
    public Optional<GetAddressResponse> setAsDefaultAddress(Long member_id, Long address_id){
        List<Address> defaultAddress = addressRepository.findByMember(memberRepository.findById(member_id).get());
        Optional<Address> target = defaultAddress.stream().filter(address -> address.getId() == address_id).findAny();
        if (target.isEmpty()) return Optional.empty();
        for (Address address1 : defaultAddress){
            if (address1.getStatus()){
                address1.setStatus(false);
                addressRepository.save(address1);
            }
        }
        target.get().setStatus(true);
        return Optional.of(addressRepository.save(target.get()).toGetAddressResponse());
    }

    // 주소지 삭제
    public ApiResponse deleteAddress(Long member_id, Long address_id){
        List<Address> addressList = addressRepository.findByMember(memberRepository.findById(member_id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다.")));
        if (addressList.size() < 2) throw new IllegalArgumentException("주소지는 최소 1개 이상 존재해야합니다.");
        addressRepository.delete(addressList.stream()
                .filter(address -> address.getId() == address_id)
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 주소지 입니다.")));
        return ApiResponse.ok("성공적으로 삭제되었습니다.");
    }

    // 주소지 수정
    public ApiResponse updateAddress(Long member_id, Long address_id, AddressDto addressDto){
        Address address = addressRepository.findById(address_id)
                .stream().filter(e -> e.getMember().getId() == member_id).findAny()
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 주소입니다."));
        address.setAddress_1(addressDto.getAddress_1());
        address.setAddress_2(addressDto.getAddress_2());
        address.setAlias(addressDto.getAlias());
        return ApiResponse.ok(addressRepository.save(address).toGetAddressResponse());
    }

}
