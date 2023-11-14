package com.world.alfs.domain.event;

import com.world.alfs.domain.TimeBaseEntity;
import com.world.alfs.domain.supervisor.Supervisor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String case1;

    @Column(nullable = false)
    private String case2;

    @Column(nullable = false)
    private int case1_cnt;

    @Column(nullable = false)
    private int case2_cnt;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    // - - - - - - - - - - - 비즈니스 로직 - - - - - - - -- - -
    public void choose(int chooseCase){
        if(chooseCase==1){
            case1_cnt+=1;
        }else{
            case2_cnt+=1;
        }

    }


}
