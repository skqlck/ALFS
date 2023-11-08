package com.world.alfs.domain.member;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private String email;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column
    private Boolean activate;

    @Builder
    public Member(Long id, String name, String identifier, String password, String birth, int point, String email, String phoneNumber, boolean activate) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.birth = birth;
        this.point = point;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activate = activate;
    }
}
