package com.world.alfs.controller.product.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String title;

    private int price;

    private int sale;

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

    private String main_img;

    private String detail_img;

    private String ingre_img;

    private int category;

    private Set<Integer> filterCode;

    @Builder
    public ProductResponse(Long id, String name, String title, int price, int sale, String delivery, String seller, String pack, String count, String weight, String allergy, String expireDate,
                           String information, String buyType, int stock, String content, String main_img, String detail_img, String ingre_img, int category, Set<Integer> filterCode) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
        this.sale = sale;
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
        this.main_img = main_img;
        this.detail_img = detail_img;
        this.ingre_img = ingre_img;
        this.category = category;
        this.filterCode = filterCode;
    }

    public void setSpecialPrice(int salePrice) {
        this.sale = salePrice;
    }

    public void setCode(Set<Integer> filterCode){
        this.filterCode = filterCode;
    }

}
