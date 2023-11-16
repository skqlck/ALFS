package com.world.alfs.domain.basket.repository;

import com.world.alfs.domain.basket.Basket;
import com.world.alfs.domain.member.Member;
import com.world.alfs.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByMemberAndStatus(Member member, int status);
    Optional<Basket> findByMemberAndProductAndStatus(Member member, Product product, int status);

    void deleteByProductId(Long id);

    Basket findByProductId(Long id);
}
