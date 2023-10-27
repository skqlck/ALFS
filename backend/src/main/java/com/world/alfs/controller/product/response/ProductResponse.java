package com.world.alfs.controller.product.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String title;

    private int price;

    private int sale;

    private String img;

    private String delivery;

    private String seller;

    private String pack;

    private String count;

    private String weight;

    private String allergy;

    private String expireDate;

    private String information;

    private String buyType;

    private int stock;

    private String content;

    @Builder
    public ProductResponse(Long id, String name, String title, int price, int sale, String img, String delivery, String seller, String pack, String count, String weight, String allergy, String expireDate, String information, String buyType, int stock, String content) {
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
