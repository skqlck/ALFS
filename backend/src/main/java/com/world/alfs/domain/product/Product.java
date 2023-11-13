package com.world.alfs.domain.product;

import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.ingredient.Ingredient;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_ingredient.ProductIngredient;
import com.world.alfs.service.product.dto.AddProductDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(
        name = "product",
        indexes = {
                @Index(name = "idx_product_name", columnList = "name")
        }
)
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
    private String delivery;

    @Column()
    private String seller;

    @Column()
    private String pack;

    @Column()
    private String count;

    @Column()
    private String weight;

    @Column(columnDefinition = "TEXT")
    private String allergy;

    @Column()
    private String expireDate;

    @Column()
    private String information;

    @Column()
    private String buyType;

    @Column()
    private int stock;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column()
    private int category;


    @Builder
    public Product(Long id, String name, String title, int price, int sale, String delivery, String seller, String pack, String count, String weight,
                   String allergy, String expireDate, String information, String buyType, int stock, String content, int category) {
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
        this.category = category;
    }

    public ProductResponse toResponse(ProductImg img) {
        return ProductResponse.builder()
                .id(id)
                .name(name)
                .title(title)
                .price(price)
                .sale(sale)
                .delivery(delivery)
                .seller(seller)
                .pack(pack)
                .count(count)
                .weight(weight)
                .allergy(allergy)
                .expireDate(expireDate)
                .information(information)
                .buyType(buyType)
                .stock(stock)
                .content(content)
                .category(category)
                .main_img(img.getImg_1())
                .detail_img(img.getImg_2())
                .ingre_img(img.getImg_3())
                .build();
    }

    public GetProductListResponse toListResponse(ProductImg img, Long pageCnt) {
        return GetProductListResponse.builder()
                .id(id)
                .title(title)
                .name(name)
                .price(price)
                .sale(sale)
                .category(category)
                .pageCnt(pageCnt)
                .img(img.getImg_1())
                .build();
    }

    //    - - - - - - - - - 비즈니스 로직 - - - - - - - - - - - - -
    public void setProduct(AddProductDto dto) {
        name = dto.getName();
        title = dto.getTitle();
        price = dto.getPrice();
        sale = dto.getSale();
        delivery = dto.getDelivery();
        seller = dto.getSeller();
        pack = dto.getPack();
        count = dto.getCount();
        weight = dto.getWeight();
        allergy = dto.getAllergy();
        expireDate = dto.getExpireDate();
        information = dto.getInformation();
        buyType = dto.getBuyType();
        stock = dto.getStock();
        content = dto.getContent();
        category = dto.getCategory();
    }

    public ProductIngredient toProductIngredient(Ingredient ingredient) {
        return ProductIngredient.builder()
                .product(this)
                .ingredient(ingredient)
                .build();
    }

}
