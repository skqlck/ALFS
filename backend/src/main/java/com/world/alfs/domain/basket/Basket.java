package com.world.alfs.domain.basket;

import com.world.alfs.controller.basket.response.GetBasketResponse;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int status; // 0 장바구니, 1 결제됨, 2 삭제

    // 구매 시점에 데이터 삽입
    private Integer purchase;

    @Builder
    public Basket(Long id, Member member, Product product, int count, int status, Integer purchase) {
        this.id = id;
        this.member = member;
        this.product = product;
        this.count = count;
        this.status = status;
        this.purchase = purchase;
    }

    // == 비지니스 로직 == //
    public void setProduct(Product product){
        this.product = product;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public void changeCount(int diff){
        this.count += diff;
    }

    public void setPurchase(int price){
        this.purchase = price * count;
    }
}
