package com.world.alfs.service.product;


import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.service.product.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public Long addProduct(AddProductDto dto) {
        return 1L;
    }
}