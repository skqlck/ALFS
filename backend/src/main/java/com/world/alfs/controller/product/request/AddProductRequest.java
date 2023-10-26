package com.world.alfs.controller.product.request;

import com.world.alfs.service.product.dto.AddProductDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductRequest {

    private Long id;

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
    public AddProductRequest(Long id, String name, String title, int price, int sale, String img, String delivery, String seller, String pack, int count, int weight, String allergy, String expireDate, String information, String buyType, int stock, String content) {
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

    public AddProductDto toDto() {
        return AddProductDto.builder()
                .id(this.id)
                .name(this.name)
                .title(this.title)
                .price(this.price)
                .sale(this.sale)
                .img(this.img)
                .delivery(this.delivery)
                .seller(this.seller)
                .pack(this.pack)
                .count(this.count)
                .weight(this.weight)
                .allergy(this.allergy)
                .expireDate(this.expireDate)
                .information(this.information)
                .buyType(this.buyType)
                .stock(this.stock)
                .content(this.content)
                .build();

    }


}
