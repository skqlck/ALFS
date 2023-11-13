package com.world.alfs.domain.supervisor;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String password;


    @Builder
    public Supervisor(Long id, String name, String identifier, String password) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
    }
}
