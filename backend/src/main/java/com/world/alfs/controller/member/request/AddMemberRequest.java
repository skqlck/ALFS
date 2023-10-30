package com.world.alfs.controller.member.request;


import com.world.alfs.service.member.dto.AddMemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AddMemberRequest {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private int birth;
    private int point;
    private String email;
    private int phoneNumber;

    @Builder
    public AddMemberRequest(Long id, String name, String identifier, String password, int birth, String address_1, String address_2, int point, String email, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.birth = birth;
        this.point = point;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public AddMemberDto toDto() {
        return AddMemberDto.builder()
                .id(id)
                .name(name)
                .identifier(identifier)
                .password(password)
                .birth(birth)
                .point(point)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
