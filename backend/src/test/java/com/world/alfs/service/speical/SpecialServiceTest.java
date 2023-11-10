package com.world.alfs.service.speical;

import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.world.alfs.domain.special.Special;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialServiceTest {
    @Mock
    private SpecialRepository specialRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SupervisorRepository supervisorRepository;


    @InjectMocks
    private SpecialService specialService;

    @Test
    @DisplayName("localdatetime 가져오는 기능 확인")
    void getLocalDateTime() {
        //given

//        AddSpecialDto specialDto = new AddSpecialDto(0,LocalDateTime.now(), LocalDateTime.now(), 5, 999, 1, 3);
//        Product product = ;
//        Supervisor supervisor = ;
//        when(specialRepository.save(any())).thenReturn(specialDto.toEntity())
//            public AddSpecialDto(int status, LocalDateTime start, java.time.LocalDateTime end, int count, int salePrice, Long productId, Long supervisorId){
//            this.status = status;
//            this.start = start;
//            this.end = end;
//            this.count = count;
//            this.salePrice = salePrice;
//            this.productId = productId;
//            this.supervisorId = supervisorId;
//        }
    }

    @Test
    @DisplayName("addSpecial 함수 테스트")
    void testAddSpecial() {

        // given
        LocalDateTime time = LocalDateTime.of(2023, 11, 10, 9, 0, 0);
        AddSpecialDto dto = new AddSpecialDto(0, time, time.plusHours(1), 5, 999, 1L, 3L);

        Product mockProduct = Product.builder()
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
        when(productRepository.findById(dto.getProductId())).thenReturn(Optional.of(mockProduct));

        Supervisor mockSupervisor = Supervisor.builder().name("Test Supervisor")
                .identifier("test")
                .password("test123")
                .build();
        when(supervisorRepository.findById(dto.getSupervisorId())).thenReturn(Optional.of(mockSupervisor));
        when(specialRepository.findById(dto.getProductId())).thenReturn(Optional.empty());

        // when
        Long result = specialService.addSpecial(dto);

        // then
//        assertNotNull(result);
        verify(productRepository, times(1)).findById(dto.getProductId());
        verify(supervisorRepository, times(1)).findById(dto.getSupervisorId());
        verify(specialRepository, times(1)).save(any(Special.class));
    }

    @Test
    @DisplayName("addSpecial 함수 테스트")
    void testAddSpecial2() {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 11, 10, 9, 5, 0);
        System.out.println(time);
        AddSpecialDto dto = new AddSpecialDto(0, time, time.plusHours(1), 5, 999, 1L, 3L);

        Product mockProduct = Product.builder()
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
        when(productRepository.findById(dto.getProductId())).thenReturn(Optional.of(mockProduct));

        Supervisor mockSupervisor = Supervisor.builder()
                .id(3L)
                .name("Test Supervisor")
                .identifier("test")
                .password("test123")
                .build();
        when(supervisorRepository.findById(dto.getSupervisorId())).thenReturn(Optional.of(mockSupervisor));
        when(specialRepository.findById(dto.getProductId())).thenReturn(Optional.empty());

        ArgumentCaptor<Special> specialCaptor = ArgumentCaptor.forClass(Special.class);

        // when
        Long result = specialService.addSpecial(dto);

        // then
        verify(productRepository, times(1)).findById(dto.getProductId());
        verify(supervisorRepository, times(1)).findById(dto.getSupervisorId());
        verify(specialRepository, times(1)).save(specialCaptor.capture());

        Special capturedSpecial = specialCaptor.getValue();
        System.out.println(capturedSpecial.toString());

        assertNotNull(capturedSpecial);
        assertEquals(dto.getStatus(), capturedSpecial.getStatus());
        assertEquals(dto.getStart(), capturedSpecial.getStart());
        assertEquals(dto.getEnd(), capturedSpecial.getEnd());
        assertEquals(dto.getCount(), capturedSpecial.getCount());
        assertEquals(dto.getSalePrice(), capturedSpecial.getSalePrice());
        assertEquals(mockProduct, capturedSpecial.getProduct());
        assertEquals(mockSupervisor, capturedSpecial.getSupervisor());

    }

}