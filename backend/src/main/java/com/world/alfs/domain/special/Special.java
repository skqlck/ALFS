package com.world.alfs.domain.special;

import com.world.alfs.domain.TimeBaseEntity;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.supervisor.Supervisor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
