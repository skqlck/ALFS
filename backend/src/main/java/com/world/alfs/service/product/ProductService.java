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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final SpecialRepository specialRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    public Long addProduct(RegisterProductDto dto) {
        Product product = dto.toEntity();
        ProductImg productImg = dto.toImgEntity(product);
        Product savedProduct = productRepository.save(product);
        productImgRepository.save(productImg);

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        Map<String, Object> map = new HashMap<>();
        map.put(String.valueOf(product.getId()), String.valueOf(product.getSale()));
        hashOperations.putAll("saleCache", map);

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

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String productIdKey = String.valueOf(dto.getId());
        Object saleValue = hashOperations.get("saleCache", productIdKey);

        if (saleValue != null) {
            try {
                hashOperations.put("saleCache", productIdKey, String.valueOf(dto.getSale()));
            } catch (NumberFormatException e) {
                log.error("Failed to parse and update sale value in Redis: {}", e.getMessage());
            }
        } else {
            log.warn("Sale value not found in Redis for productId: {}", dto.getId());
        }

        return product.get().getId();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductId(Long pageCnt, int page, int status) {
        Long start = (long) ((page - 1) * 15 + 1);
        Long end = start + 14;
        if (pageCnt == page) {
            end = countProduct();
        }

        List<Product> productList;
        if (status == 0) { // id 낮은 순 (최신순)
            productList = productRepository.findByIdBetween(start, end);
        } else if (status == 1) { // sale 높은순
            PageRequest pageRequest = PageRequest.of(page - 1, 15);
            Page<Product> productPage = productRepository.findAllByOrderBySaleDescIdAsc(pageRequest);
            productList = productPage.getContent();
        } else {
            PageRequest pageRequest = PageRequest.of(page - 1, 15);
            Page<Product> productPage = productRepository.findAllByOrderBySaleAscIdAsc(pageRequest);
            productList = productPage.getContent();
        }

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
        }
        return productResponseList;
    }

    public List<ProductResponse> getAllProductResponse(List<Product> productList) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            ProductImg img = productImgRepository.findByProductId(productList.get(i).getId());
            productResponseList.add(productList.get(i).toListProductResponse(img));
        }
        return productResponseList;
    }

    public Long deleteProduct(Long id) {
        productRepository.deleteById(id);

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String productIdKey = String.valueOf(id);

        Long deletedFieldsCount = hashOperations.delete("saleCache", productIdKey);

        if (deletedFieldsCount != null && deletedFieldsCount > 0) {
            log.info("Successfully deleted the field for productId: {}", id);
        } else {
            log.warn("No field found to delete for productId: {}", id);
        }

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

    public List<Product> getSearchResultProduct(String word) {
        List<Product> productList = productRepository.findByNameContains(word);
        return productList;
    }

}