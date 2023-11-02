package com.world.alfs.domain.special;

import com.world.alfs.domain.TimeBaseEntity;
import com.world.alfs.domain.product.Product;
import com.world.alfs.domain.supervisor.Supervisor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Special extends TimeBaseEntity {

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

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

}
