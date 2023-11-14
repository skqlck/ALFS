package com.world.alfs.service.product;


import com.world.alfs.common.exception.CustomException;
import com.world.alfs.controller.product.response.GetProductListResponse;
import com.world.alfs.controller.product.response.ProductResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.product_img.ProductImg;
import com.world.alfs.domain.product_img.repostiory.ProductImgRepository;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.service.product.dto.AddProductDto;
import com.world.alfs.service.product.dto.RegisterProductDto;
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
    private final SpecialRepository specialRepository;

    public Long addProduct(RegisterProductDto dto) {
        Product product = dto.toEntity();
        ProductImg productImg = dto.toImgEntity(product);
        Product savedProduct = productRepository.save(product);
        productImgRepository.save(productImg);
        return savedProduct.getId();
    }

    public List<Product> getProduct(Long id) {
        List<Product> productList = new ArrayList<>();
        Optional<Product> product = productRepository.findById(id);
        productList.add(product.get());
        return productList;
    }

    public Long setProduct(AddProductDto dto) {
        Optional<Product> product = productRepository.findById(dto.getId());
        product.get().setProduct(dto);
        return product.get().getId();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductId(Long pageCnt, int page) {
        Long start = (long) ((page - 1) * 15 + 1);
        Long end = start + 14;
        if (pageCnt == page) {
            end = countProduct();
        }
        List<Product> productList = productRepository.findByIdBetween(start, end);
        return productList;
    }

    public List<Product> getCategoryProduct(int category) {
        List<Product> productList = productRepository.findByCategory(category);
        return productList;
    }

    public List<GetProductListResponse> getAllProduct(List<Product> productList) {
        List<GetProductListResponse> productResponseList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            ProductImg img = productImgRepository.findByProductId(productList.get(i).getId());
            productResponseList.add(productList.get(i).toListResponse(img, countPage()));

            Optional<Special> special = specialRepository.findById(productList.get(i).getId());
            if (special.isPresent()) {
                int status = specialRepository.findByStatus(productList.get(i).getId());
                if (status == 1) {
                    productResponseList.get(i).setSpecialPrice(special.get().getSalePrice());
                }
            }
        }
        return productResponseList;
    }

    public List<ProductResponse> getAllProductResponse(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            ProductImg img = productImgRepository.findByProductId(productList.get(i).getId());
            productResponseList.add(productList.get(i).toListProductResponse(img));

            Optional<Special> special = specialRepository.findById(productList.get(i).getId());
            if (special.isPresent()) {
                int status = specialRepository.findByStatus(productList.get(i).getId());
                if (status == 1) {
                    productResponseList.get(i).setSpecialPrice(special.get().getSalePrice());
                }
            }
        }
        return productResponseList;
    }

    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    public Long countPage() {
        Long cntProduct = productRepository.count();
        if (cntProduct % 15 == 0) {
            return cntProduct / 15;
        } else {
            return cntProduct / 15 + 1;
        }
    }

    public Long countProduct() {
        return productRepository.count();
    }

}