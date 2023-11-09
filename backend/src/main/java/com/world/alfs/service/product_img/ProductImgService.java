package com.world.alfs.service.product_img;

import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductImgService {
    private final ProductImgRepository productImgRepository;

    public void deleteProductImg(Long id) {
        productImgRepository.deleteByProductId(id);
    }
}