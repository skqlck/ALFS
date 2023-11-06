package com.world.alfs.service.speical;

import com.world.alfs.common.exception.CustomException;
import com.world.alfs.common.exception.ErrorCode;
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

    public Long addSpecial(AddSpecialDto dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()->new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        Supervisor supervisor = supervisorRepository.findById(dto.getSupervisorId())
                .orElseThrow(()->new CustomException(ErrorCode.SUPERVISOR_NOT_FOUND));

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

    public GetSpecialResponse getSpecial(Long id){
        Special special = specialRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        return GetSpecialResponse.toGetSpecialListResponse(special);
    }

//    public Long setProduct(Long id, AddSpecialDto dto){
//
//    }
//
//    public Long deleteProduct(Long id){
//
//    }


}