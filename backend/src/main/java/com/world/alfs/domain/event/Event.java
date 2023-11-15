package com.world.alfs.domain.event;

import com.world.alfs.controller.event.response.GetEventResponse;
import com.world.alfs.domain.supervisor.Supervisor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

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

    @Column
    private int status;

    @Column
    private LocalDateTime start;

    @Column
    private LocalDateTime end;


    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    // - - - - - - - - - - - 비즈니스 로직 - - - - - - - -- - -
    public void choose(int chooseCase) {
        if (chooseCase == 1) {
            case1_cnt += 1;
        } else {
            case2_cnt += 1;
        }

    }

    @Builder
    public Event(Long id, String title, String case1, String case2, int case1_cnt, int case2_cnt, LocalDateTime start, LocalDateTime end, Supervisor supervisor, int status) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.case1 = case1;
        this.case2 = case2;
        this.case1_cnt = case1_cnt;
        this.case2_cnt = case2_cnt;
        this.start = start;
        this.end = end;
        this.supervisor = supervisor;
    }

    public GetEventResponse toResponse() {
        return GetEventResponse.builder()
                .case1(this.case1)
                .case2(this.case2)
                .title(this.title)
                .build();
    }
}
