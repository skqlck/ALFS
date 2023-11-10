package com.world.alfs.domain.special;

import com.world.alfs.IntegrationTestSupport;
import com.world.alfs.common.exception.CustomException;
import com.world.alfs.common.exception.ErrorCode;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.product.dto.AddProductDto;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
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

        // You can also use findById to retrieve the entities and assert their existence
        Optional<Product> savedProduct = productRepository.findById(product.getId());
        assertTrue(savedProduct.isPresent(), "Product should be present in the database");

        Optional<Supervisor> savedSupervisor = supervisorRepository.findById(supervisor.getId());
        assertTrue(savedSupervisor.isPresent(), "Supervisor should be present in the database");

        // Additional assertions based on your entity structure
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

        Special checkSpecial = specialRepository.findByStart(time);
        System.out.println(checkSpecial.toString());

        // then
//        for (Special s : checkSpecial) {
//            System.out.println(s.toString());
////            Assertions.assertThat(s.getProduct().getId()).isEqualTo(special.getProduct().getId());
//        }

        // when
//        Special foundSpecial = specialRepository.findById(special.getId()).orElse(null);
//
//        // then
//        assertNotNull(foundSpecial);
//        System.out.println("Found Special: " + foundSpecial);
    }


}