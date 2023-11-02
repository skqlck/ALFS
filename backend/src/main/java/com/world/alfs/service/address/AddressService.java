package com.world.alfs.service.address;

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

}
