package com.world.alfs.domain.special;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpecialTest {
    @Mock
    private SpecialRepository specialRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private SupervisorRepository supervisorRepository;

    @Test
    @Transactional
    void testProductSupervisor() {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 11, 10, 9, 0, 0);

        Product product = Product.builder()
                .id(5000L)
                .name("테스트 상품")
                .title("테스트 상품")
                .price(5000)
                .sale(4500)
                .delivery("배달")
                .seller("seller")
                .pack("pack")
                .count("count")
                .weight("weight")
                .allergy("allergy")
                .expireDate("expireDate")
                .information("information")
                .buyType("buyType")
                .stock(50)
                .content("content")
                .category(0).build();
        Supervisor supervisor = Supervisor.builder()
                .id(3L)
                .name("Test Supervisor")
                .identifier("test")
                .password("test123")
                .build();

        // when
        productRepository.save(product);
        supervisorRepository.save(supervisor);

        // then
        assertNotNull(product.getId(), "Product ID should not be null after save");
        assertNotNull(supervisor.getId(), "Supervisor ID should not be null after save");

        Optional<Product> savedProduct = productRepository.findById(product.getId());
        assertTrue(savedProduct.isPresent(), "Product should be present in the database");

        Optional<Supervisor> savedSupervisor = supervisorRepository.findById(supervisor.getId());
        assertTrue(savedSupervisor.isPresent(), "Supervisor should be present in the database");

        assertEquals(product.getName(), savedProduct.get().getName(), "Product name should match");
        assertEquals(supervisor.getName(), savedSupervisor.get().getName(), "Supervisor name should match");

    }

    @Test
    @Transactional
    void testFindByLocalDateTime() {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 11, 10, 9, 0, 0);

        Product product = Product.builder()
                .id(5000L)
                .name("테스트 상품")
                .title("테스트 상품")
                .price(5000)
                .sale(4500)
                .delivery("배달")
                .seller("seller")
                .pack("pack")
                .count("count")
                .weight("weight")
                .allergy("allergy")
                .expireDate("expireDate")
                .information("information")
                .buyType("buyType")
                .stock(50)
                .content("content")
                .category(0).build();
        Supervisor supervisor = Supervisor.builder().name("Test Supervisor")
                .identifier("test")
                .password("test123")
                .build();

        productRepository.save(product);
        supervisorRepository.save(supervisor);

        Special special = Special.builder()
                .id(product.getId())
                .status(1)
                .count(5)
                .salePrice(999)
                .start(time)
                .end(time.plusHours(1))
                .product(product)
                .supervisor(supervisor)
                .build();

        specialRepository.save(special);

        // when
        // then
        List<Special> checkSpecial = specialRepository.findByStart(time);
        System.out.println(checkSpecial.toString());
    }
}