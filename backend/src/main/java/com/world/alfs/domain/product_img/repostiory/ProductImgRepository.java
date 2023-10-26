package com.world.alfs.domain.product_img.repostiory;

import com.world.alfs.domain.product_img.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

}
