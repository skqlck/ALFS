package com.world.alfs.domain.address;

import com.world.alfs.controller.address.response.GetAddressResponse;
import com.world.alfs.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address_1;

    private String address_2;

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Address(String address_1, String address_2, String alias, Boolean status, Member member) {
        this.address_1 = address_1;
        this.address_2 = address_2;
        this.alias = alias;
        this.status = status;
        this.member = member;
    }

    public GetAddressResponse toGetAddressResponse(){
        return GetAddressResponse.builder()
                .id(id)
                .address_1(address_1)
                .address_2(address_2)
                .status(status)
                .alias(alias)
                .build();
    }

    // ===== 비지니스 로직 ===== //
    public void setMember(Member member){
        this.member = member;
    }

    public void setStatus() {
        this.status = !this.status;
    }
}
