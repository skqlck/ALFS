package com.world.alfs.domain.member;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int birth;

    @Column(nullable = false)
    private String address_1;

    private String address_2;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number",nullable = false)
    private int phoneNumber;

//    @OneToMany(mappedBy = "member")
//    private List<Basket> baskets = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Board> boards = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<MemberAllergy> memberAllergies = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Wining> winings = new ArrayList<>();

}
