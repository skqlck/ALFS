package com.world.alfs.domain.manufacturing_allergy.repository;

import com.world.alfs.domain.allergy.Allergy;
import com.world.alfs.domain.manufacturing_allergy.ManufacturingAllergy;
import com.world.alfs.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturingAllergyRepository extends JpaRepository<ManufacturingAllergy, Long> {

    @Query("SELECT COUNT(ma) FROM ManufacturingAllergy ma WHERE ma.product.id = :productId AND ma.allergy.id IN :allergyIdList")
    int findCountByProductAndAllergy(Long productId, List<Long> allergyIdList);

    boolean existsByProductAndAllergy(Product product, Allergy allergy);

}
