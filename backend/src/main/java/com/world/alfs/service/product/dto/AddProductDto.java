package com.world.alfs.service.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddProductDto {

    private String name;

    private String title;

    private int price;

    private int sale;

    private String img;

    private String delivery;

    private String seller;

    private String pack;

    private int count;

    private int weight;

    private String allergy;

    private String expireDate;

    private String information;

    private String buyType;

    private int stock;

    private String content;

    @Builder
    public AddProductDto(String name, String title, int price, int sale, String img, String delivery, String seller, String pack, int count, int weight, String allergy, String expireDate, String information, String buyType, int stock, String content) {
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
