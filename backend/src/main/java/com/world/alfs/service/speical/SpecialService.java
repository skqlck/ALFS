package com.world.alfs.service.speical;

import com.world.alfs.controller.speical.response.GetSpecialResponse;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.product.repository.ProductRepository;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.domain.supervisor.repository.SupervisorRepository;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SpecialService {

    private final SpecialRepository specialRepository;
    private final ProductRepository productRepository;
    private final SupervisorRepository supervisorRepository;

    public Long addSpecial(AddSpecialDto dto) throws Exception {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()->new Exception("이벤트 상품을 등록할 상품이 없습니다."));
        Supervisor supervisor = supervisorRepository.findById(dto.getSupervisorId())
                .orElseThrow(()->new Exception("이벤트 상품을 등록할 관리자가 없습니다."));

        Special special = dto.toEntity(product, supervisor);
        specialRepository.save(special);

        return special.getId();
    }

    public List<GetSpecialResponse> getAllSpecial(){

        List<Special> specialList = specialRepository.findAll();

        List<GetSpecialResponse> specialResponseList = new ArrayList<>();
        for (Special special : specialList) {
            GetSpecialResponse specialResponse = GetSpecialResponse.toGetSpecialListResponse(special);
            specialResponseList.add(specialResponse);
        }

        return specialResponseList;
    }

//    public Long getProduct(Long id){
//
//    }
//
//    public Long setProduct(Long id, AddSpecialDto dto){
//
//    }
//
//    public Long deleteProduct(Long id){
//
//    }


}