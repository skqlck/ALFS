package com.world.alfs.domain.product.repository;

import com.world.alfs.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdBetween(Long startId, Long endId);
    void deleteById(Long id);

}
