package com.world.alfs.service.product;


import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import com.world.alfs.service.product.dto.AddProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;

    public Long addProduct(AddProductDto dto) {
        Product product = dto.toEntity();
        ProductImg productImg = dto.toImgEntity();
        Product savedProduct = productRepository.save(product);
        productImgRepository.save(productImg);
        return savedProduct.getId();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Long setProduct(Long id, int price, int sale) {
        Optional<Product> product = productRepository.findById(id);
        product.get().setProduct(price,sale);
        return product.get().getId();
    }

    public List<GetProductListResponse> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<GetProductListResponse> productResponseList = new ArrayList<>();
        GetProductListResponse response = null;
        for(int i=0; i<productList.size(); i++){
//            ProductImg img = productImgRepository.findProductImgById(productList.get(i).getId());
            System.out.println(productList.get(i).getId());
            GetProductListResponse getProductListResponse = GetProductListResponse.builder()
                    .id(productList.get(i).getId())
                    .title(productList.get(i).getTitle())
                    .name(productList.get(i).getName())
                    .price(productList.get(i).getPrice())
                    .sale(productList.get(i).getSale())
                    .build();
//            response.toGetProductListResponse(productList.get(i));
            productResponseList.add(getProductListResponse);
        }
        return productResponseList;
    }

    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
//        productImgRepository.deleteProductImgByProductId(id);
        productImgRepository.deleteById(id);
        return id;

    }
}