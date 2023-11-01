package com.world.alfs.domain.product_img.repostiory;

import com.world.alfs.domain.product_img.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    ProductImg findByProductId(Long productId);

//    @Query("DELETE FROM ProductImg p WHERE p.product_id=:id")
//    void deleteProductImgByProductId(@Param("id") Long id);

}
