package com.world.alfs.domain.special;

import com.world.alfs.domain.TimeBaseEntity;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.supervisor.Supervisor;
import com.world.alfs.service.speical.dto.AddSpecialDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Special {

    @Id
    private Long id;

    @MapsId // Special.id에 매핑
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column
    private int status;

    @Column
    private int count;

    @Column
    private int salePrice;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @Builder
    public Special(Long id, Product product, int status, int count, int salePrice, Supervisor supervisor, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.product = product;
        this.status = status;
        this.count = count;
        this.salePrice = salePrice;
        this.supervisor = supervisor;
        this.start = start;
        this.end = end;
    }

    public void setSpecial(AddSpecialDto dto, Product product, Supervisor supervisor){
        this.status = dto.getStatus();
        this.count = dto.getCount();
        this.salePrice = dto.getSalePrice();
        this.start = dto.getStart();
        this.end = dto.getEnd();
        this.product = product;
        this.supervisor = supervisor;
    }
}
