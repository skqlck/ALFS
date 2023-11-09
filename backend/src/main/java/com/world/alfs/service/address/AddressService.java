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
    public ApiResponse addAddress(AddressDto addressDto, Long member_id, Boolean status){
        // TODO : 주소지 확인 로직

        Member member = memberRepository.findById(member_id).stream()
                .filter(m -> m.getActivate()).findAny()
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Address> addressList = addressRepository.findByMember(member);
        if (addressList.size() > 4) throw new IllegalArgumentException("주소지는 최대 5개까지 추가할 수 있습니다.");

        addressDto.setMember(member);
        Address address = addressRepository.save(addressDto.toEntity(status));
        return ApiResponse.created("주소지 추가에 성공했습니다.", address.toGetAddressResponse());
    }

    // 주소지 조회
    public List<GetAddressResponse> getAddress(Long id){
        Member member = memberRepository.findById(id)
                .filter(m -> m.getActivate())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Address> addressList = addressRepository.findByMember(member);
        List<GetAddressResponse> addressResponseList = new ArrayList<>();
        for (Address address : addressList){
            addressResponseList.add(address.toGetAddressResponse());
        }
        return addressResponseList;
    }

    // 기본 주소지로 설정
    public ApiResponse setAsDefaultAddress(Long member_id, Long address_id){
        Member member = memberRepository.findById(member_id)
                .filter(m -> m.getActivate())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Address> registeredAddress = addressRepository.findByMember(member);
        Address target = registeredAddress.stream()
                .filter(address -> address.getId() == address_id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주소지입니다."));
        for (Address address1 : registeredAddress){
            if (address1.getStatus()){
                address1.setStatus(false);
                addressRepository.save(address1);
            }
        }
        target.setStatus(true);

        return ApiResponse.ok(addressRepository.save(target).toGetAddressResponse());
    }

    // 주소지 삭제
    public ApiResponse deleteAddress(Long member_id, Long address_id){
        List<Address> addressList = addressRepository.findByMember(
                memberRepository.findById(member_id)
                        .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."))
        );
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
