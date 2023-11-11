package com.world.alfs.config.batch.special;

import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;
import org.hibernate.Hibernate;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener {

    private final SpecialRepository specialRepository;

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            log.debug("============================================");
            log.debug("                JOB STARTED                 ");
            log.debug("============================================");

            List<Special> specials = specialRepository.findAll();
//            specials.forEach(special -> {
//                Hibernate.initialize(special.getProduct());
//                log.debug(">>> Found " + special.toString());
//            });
//            log.debug(specials.toString());
//            log.debug("============================================");
        }
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.debug("============================================");
            log.debug("                JOB FINISHED                ");
            log.debug("============================================");

            List<Special> specials = specialRepository.findAll();
//            specials.forEach(special -> {
//                Hibernate.initialize(special.getProduct());
//                log.debug(">>> Found " + special.toString());
//            });
//            log.debug(specials.toString());
//            log.debug("============================================");
        }
    }
}