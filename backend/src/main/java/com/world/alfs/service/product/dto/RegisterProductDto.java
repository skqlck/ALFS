package com.world.alfs.service.product.dto;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_img.ProductImg;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterProductDto {
    private String name;

    private String title;

    private int price;

    private int sale;

    private String img_1;

    private String img_2;

    private String img_3;

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

    private Integer category;

    @Builder
    public RegisterProductDto(String name, String title, int price, int sale, String img_1, String img_2, String img_3, String delivery, String seller, String pack, String count, String weight, String allergy, String expireDate, String information, String buyType, int stock, String content, Integer category) {
        this.name = name;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.img_1 = img_1;
        this.img_2 = img_2;
        this.img_3 = img_3;
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
        this.category = category;
    }

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .title(this.title)
                .price(this.price)
                .sale(this.sale)
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
                .category(this.category)
                .build();

    }

    public ProductImg toImgEntity(Product product) {
        return ProductImg.builder()
                .product(product)
                .img_1(img_1)
                .img_2(img_2)
                .img_3(img_3)
                .build();
    }

}
