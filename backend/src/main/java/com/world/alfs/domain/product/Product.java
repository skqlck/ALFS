package com.world.alfs.domain.product;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String title;

    @Column()
    private int price;

    @Column()
    private int sale;

    @Column()
    private String img;

    @Column()
    private String delivery;

    @Column()
    private String seller;

    @Column()
    private String pack;

    @Column()
    private int count;

    @Column()
    private int weight;

    @Column()
    private String allergy;

    @Column()
    private String expireDate;

    @Column()
    private String information;

    @Column()
    private String buyType;

    @Column()
    private int stock;

    @Column()
    private String content;




}
