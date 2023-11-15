package com.world.alfs.config.batch.special;

import com.world.alfs.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.persistence.EntityManagerFactory;

@Slf4j
public class CustomJpaProductEndItemProcessor implements ItemProcessor<Product, Product> {
    private final EntityManagerFactory entityManagerFactory;
    private final RedisTemplate<String, Object> redisTemplate;

    public CustomJpaProductEndItemProcessor(EntityManagerFactory entityManagerFactory, RedisTemplate<String, Object> redisTemplate) {
        this.entityManagerFactory = entityManagerFactory;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product process(Product item) {
        log.info("process");
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String productIdKey = String.valueOf(item.getId());
        Object saleValue = hashOperations.get("saleCache", productIdKey);

        if (saleValue != null) {
            try {
                int sale = Integer.parseInt(saleValue.toString());
                item.setSale(sale); // Product 객체의 sale을 기존 가격에서 특가할인가격으로 변경
            } catch (NumberFormatException e) {
                log.error("Failed to parse sale value from Redis: {}", e.getMessage());
            }
        } else {
            log.warn("Sale value not found in Redis for productId: {}", item.getId());
        }

        return item;
    }
}