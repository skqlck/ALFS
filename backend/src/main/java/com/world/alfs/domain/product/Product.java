package com.world.alfs.domain.product;


import lombok.AccessLevel;
import lombok.Builder;
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


    @Builder
    public Product(Long id, String name, String title, int price, int sale, String img, String delivery, String seller, String pack, int count, int weight, String allergy, String expireDate, String information, String buyType, int stock, String content) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.img = img;
        this.delivery = delivery;
        this.seller = seller;
        this.pack = pack;
        this.count = count;
        this.weight = weight;
        this.allergy = allergy;
        this.expireDate = expireDate;
        this.information = information;
        this.buyType = buyType;
        this.stock = stock;
        this.content = content;
    }
}
