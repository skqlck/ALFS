package com.world.alfs.controller.product.response;

import com.world.alfs.domain.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class GetProductListResponse {
    private Long id;

    private String name;

    private String title;

    private int price;

    private int sale;

    private String img;

    private int category;

    private Long pageCnt;

    private Set<Integer> filterCode;

    private List<String> hates;

    private List<String> allergies;

    private Boolean isSpecial;

    @Builder
    public GetProductListResponse(Long id, String name, String title, int price, int sale, String img, int category, Long pageCnt, Set<Integer> filterCode, List<String> hates, List<String> allergies, Boolean isSpecial) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.img = img;
        this.category = category;
        this.pageCnt = pageCnt;
        this.filterCode = filterCode;
        this.hates = hates;
        this.allergies = allergies;
        this.isSpecial = isSpecial;
    }


    public GetProductListResponse toGetProductListResponse(Product product) {
        return GetProductListResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .title(product.getTitle())
                .price(product.getPrice())
                .sale(product.getSale())
                .filterCode(null)
                .allergies(null)
                .hates(null)
                .build();
    }

    public void setCode(Set<Integer> filterCode) {
        this.filterCode = filterCode;
    }

    public void setAllergyDetail(List<String> str) {
        this.allergies = str;
    }

    public void setHateDetail(List<String> str) {
        this.hates = str;
    }



}
