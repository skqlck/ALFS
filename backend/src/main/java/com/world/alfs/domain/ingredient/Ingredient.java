package com.world.alfs.domain.ingredient;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {

    @Id
    private Long id;

    @Column
    private String name;

    @Builder
    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
