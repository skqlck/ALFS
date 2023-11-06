package com.world.alfs.controller.speical.response;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.special.Special;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GetSpecialResponse {

    // product
    private Long productId;
    private String productName;
    private String productTitle;
    private int productPrice;
    private int productSale;
    private String productDelivery;
    private String productSeller;
    private String productPack;
    private String productCount;
    private String productWeight;
    private String productAllergy;
    private String productExpireDate;
    private String productInformation;
    private String productBuyType;
    private int productStock;
    private String productContent;

    // special
    private Long supervisorId;
    private int status;
    private int count;
    private int salePrice;
    private LocalDateTime start;
    private LocalDateTime end;

    @Builder
    public GetSpecialResponse(Long productId, String productName, String productTitle, int productPrice, int productSale, String productDelivery, String productSeller, String productPack, String productCount, String productWeight, String productAllergy, String productExpireDate, String productInformation, String productBuyType, int productStock, String productContent, Long supervisorId, int status, int count, int salePrice, LocalDateTime start, LocalDateTime end) {
        this.productId = productId;
        this.productName = productName;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productSale = productSale;
        this.productDelivery = productDelivery;
        this.productSeller = productSeller;
        this.productPack = productPack;
        this.productCount = productCount;
        this.productWeight = productWeight;
        this.productAllergy = productAllergy;
        this.productExpireDate = productExpireDate;
        this.productInformation = productInformation;
        this.productBuyType = productBuyType;
        this.productStock = productStock;
        this.productContent = productContent;
        this.supervisorId = supervisorId;
        this.status = status;
        this.count = count;
        this.salePrice = salePrice;
        this.start = start;
        this.end = end;
    }

    public static GetSpecialResponse toGetSpecialListResponse(Special special){
        return GetSpecialResponse.builder()
                .productId(special.getProduct().getId())
                .productName(special.getProduct().getName())
                .productTitle(special.getProduct().getTitle())
                .productPrice(special.getProduct().getPrice())
                .productSale(special.getProduct().getSale())
                .productDelivery(special.getProduct().getDelivery())
                .productSeller(special.getProduct().getSeller())
                .productPack(special.getProduct().getPack())
                .productCount(special.getProduct().getCount())
                .productWeight(special.getProduct().getWeight())
                .productAllergy(special.getProduct().getAllergy())
                .productExpireDate(special.getProduct().getExpireDate())
                .productInformation(special.getProduct().getInformation())
                .productBuyType(special.getProduct().getBuyType())
                .productStock(special.getProduct().getStock())
                .productContent(special.getProduct().getContent())
                .supervisorId(special.getSupervisor().getId())
                .status(special.getStatus())
                .count(special.getCount())
                .salePrice(special.getSalePrice())
                .start(special.getStart())
                .end(special.getEnd())
                .build();
    }
}
