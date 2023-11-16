package com.world.alfs.controller.speical.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private int productCategory;

    private String productImg;
    private String productMainImg;
    private String productDetailImg;
    private String productIngreImg;
    private Set<Integer> filterCode;
    private boolean isSpeical;

    // special
    private Long supervisorId;
    private int status;
    private int count;
    private int salePrice;
    private LocalDateTime start;
    private LocalDateTime end;


    public void setCode(Set<Integer> filterCode) {
        this.filterCode = filterCode;
    }
    public void setIsSpecial(boolean check) { this.isSpeical = check; }
}
