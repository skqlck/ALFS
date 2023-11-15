package com.world.alfs.domain.special;

import com.world.alfs.controller.speical.response.GetSpecialListResponse;
import com.world.alfs.controller.speical.response.GetSpecialResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.service.speical.dto.SetSpecialDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Special {

    @Id
    private Long id;

    @MapsId // Special.id에 매핑
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private int status;

    @Column
    private int count;

    @Column
    private int salePrice;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @Builder
    public Special(Long id, Product product, int status, int count, int salePrice, Supervisor supervisor, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.product = product;
        this.status = status;
        this.count = count;
        this.salePrice = salePrice;
        this.supervisor = supervisor;
        this.start = start;
        this.end = end;
    }

    public void setSpecial(SetSpecialDto dto, Product product, Supervisor supervisor) {
        this.status = dto.getStatus();
        this.count = dto.getCount();
        this.salePrice = dto.getSalePrice();
        this.start = dto.getStart();
        this.end = dto.getEnd();
        this.product = product;
        this.supervisor = supervisor;
    }

    public GetSpecialListResponse toGetSpecialListResponse(ProductImg img) {
        return GetSpecialListResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productTitle(product.getTitle())
                .productPrice(product.getPrice())
                .productSale(product.getSale())
                .productDelivery(product.getDelivery())
                .productSeller(product.getSeller())
                .productPack(product.getPack())
                .productCount(product.getCount())
                .productWeight(product.getWeight())
                .productAllergy(product.getAllergy())
                .productExpireDate(product.getExpireDate())
                .productInformation(product.getInformation())
                .productBuyType(product.getBuyType())
                .productStock(product.getStock())
                .productContent(product.getContent())
                .productCategory(product.getCategory())
                .productImg(img.getImg_1())
                .supervisorId(supervisor.getId())
                .status(status)
                .count(count)
                .salePrice(salePrice)
                .start(start)
                .end(end)
                .build();
    }

    public GetSpecialResponse toGetSpecialResponse(ProductImg img) {
        return GetSpecialResponse.builder()
                .productId(product.getId())
                .productName(product.getName())
                .productTitle(product.getTitle())
                .productPrice(product.getPrice())
                .productSale(product.getSale())
                .productDelivery(product.getDelivery())
                .productSeller(product.getSeller())
                .productPack(product.getPack())
                .productCount(product.getCount())
                .productWeight(product.getWeight())
                .productAllergy(product.getAllergy())
                .productExpireDate(product.getExpireDate())
                .productInformation(product.getInformation())
                .productBuyType(product.getBuyType())
                .productStock(product.getStock())
                .productContent(product.getContent())
                .productCategory(product.getCategory())
                .productMainImg(img.getImg_1())
                .productDetailImg(img.getImg_2())
                .productIngreImg(img.getImg_3())
                .supervisorId(supervisor.getId())
                .status(status)
                .count(count)
                .salePrice(salePrice)
                .start(start)
                .end(end)
                .build();
    }

    // 비즈니스 로직
    public void changeCount(int diff) {
        this.count -= diff;
    }
}
