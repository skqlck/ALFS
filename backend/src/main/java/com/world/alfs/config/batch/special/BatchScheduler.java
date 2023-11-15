package com.world.alfs.config.batch.special;

import com.world.alfs.domain.event.Event;
import com.world.alfs.domain.event.repository.EventRepository;
import com.world.alfs.domain.special.Special;
import com.world.alfs.domain.special.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private BatchJobConfiguration batchJobConfiguration;

    private final SpecialRepository specialRepository;
    private final EventRepository eventRepository;


    @Scheduled(fixedRate = 600000) // 10분단위
    public void runSpecialStartJob() {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String parsedTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        log.debug("현시간:"+parsedTime);
        LocalDateTime parsedDateTime = LocalDateTime.parse(parsedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Event> events = eventRepository.findByStart(parsedDateTime);
        List<Special> specials = specialRepository.findByStart(parsedDateTime);


        for (Event event : events) {
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("currentTime", System.currentTimeMillis())
                        .addLong("supervisorId", event.getSupervisor().getId())
                        .addLong("eventId", event.getId())
                        .toJobParameters();

                JobExecution jobExecution = jobLauncher.run(batchJobConfiguration.eventStartJob(), jobParameters);

                // 여기서 JobExecution 결과를 처리하거나 로깅할 수 있습니다.
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    // 성공적으로 완료된 경우의 로직
                    log.debug("Job Execution: " + jobExecution.getStatus());
                    log.debug("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
                    log.debug("Job getJobId: " + jobExecution.getJobId());
                    log.debug("Job getExitStatus: " + jobExecution.getExitStatus());
                    log.debug("Job getJobInstance: " + jobExecution.getJobInstance());
                    log.debug("Job getStepExecutions: " + jobExecution.getStepExecutions());
                    log.debug("Job getLastUpdated: " + jobExecution.getLastUpdated());
                    log.debug("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
                } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // 실패한 경우의 로직
                    log.debug("jobExecution 이 실패한 경우");
                }
            } catch (Exception e) {
                // 예외 처리 로직
                log.debug("jobExecution 이 실패했습니다");
                log.error(e.getMessage());
            }

        }

        for (Special special : specials) {
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("currentTime", System.currentTimeMillis())
                        .addLong("supervisorId", special.getSupervisor().getId())
                        .addLong("productId", special.getProduct().getId())
                        .toJobParameters();

                JobExecution jobExecution = jobLauncher.run(batchJobConfiguration.specialStartJob(), jobParameters);

                // 여기서 JobExecution 결과를 처리하거나 로깅할 수 있습니다.
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    // 성공적으로 완료된 경우의 로직
                    log.debug("Job Execution: " + jobExecution.getStatus());
                    log.debug("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
                    log.debug("Job getJobId: " + jobExecution.getJobId());
                    log.debug("Job getExitStatus: " + jobExecution.getExitStatus());
                    log.debug("Job getJobInstance: " + jobExecution.getJobInstance());
                    log.debug("Job getStepExecutions: " + jobExecution.getStepExecutions());
                    log.debug("Job getLastUpdated: " + jobExecution.getLastUpdated());
                    log.debug("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
                } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // 실패한 경우의 로직
                    log.debug("jobExecution 이 실패한 경우");
                }
            } catch (Exception e) {
                // 예외 처리 로직
                log.debug("jobExecution 이 실패했습니다");
                log.error(e.getMessage());
            }
        }
    }

    @Scheduled(fixedRate = 1000) // 1초마다
    public void runSpecialEndJob() {
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String parsedTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        log.debug("현시간:"+parsedTime);

        LocalDateTime parsedDateTime = LocalDateTime.parse(parsedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Special> specials = specialRepository.findByEnd(parsedDateTime);
        List<Event> events = eventRepository.findByEnd(parsedDateTime);
//        log.info("specials = {}", specials.toString());


        for (Event event : events) {
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("currentTime", System.currentTimeMillis())
                        .addLong("supervisorId", event.getSupervisor().getId())
                        .addLong("eventId", event.getId())
                        .toJobParameters();

                JobExecution jobExecution = jobLauncher.run(batchJobConfiguration.eventEndJob(), jobParameters);

                // 여기서 JobExecution 결과를 처리하거나 로깅할 수 있습니다.
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    // 성공적으로 완료된 경우의 로직
                    log.debug("Job Execution: " + jobExecution.getStatus());
                    log.debug("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
                    log.debug("Job getJobId: " + jobExecution.getJobId());
                    log.debug("Job getExitStatus: " + jobExecution.getExitStatus());
                    log.debug("Job getJobInstance: " + jobExecution.getJobInstance());
                    log.debug("Job getStepExecutions: " + jobExecution.getStepExecutions());
                    log.debug("Job getLastUpdated: " + jobExecution.getLastUpdated());
                    log.debug("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
                } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // 실패한 경우의 로직
                    log.debug("jobExecution 이 실패한 경우");
                }
            } catch (Exception e) {
                // 예외 처리 로직
                log.debug("jobExecution 이 실패했습니다");
                log.error(e.getMessage());
            }
        }

        for (Special special : specials) {
            try {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("currentTime", System.currentTimeMillis())
                        .addLong("supervisorId", special.getSupervisor().getId())
                        .addLong("productId", special.getProduct().getId())
                        .toJobParameters();

                JobExecution jobExecution = jobLauncher.run(batchJobConfiguration.specialEndJob(), jobParameters);

                // 여기서 JobExecution 결과를 처리하거나 로깅할 수 있습니다.
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    // 성공적으로 완료된 경우의 로직
                    log.debug("Job Execution: " + jobExecution.getStatus());
                    log.debug("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
                    log.debug("Job getJobId: " + jobExecution.getJobId());
                    log.debug("Job getExitStatus: " + jobExecution.getExitStatus());
                    log.debug("Job getJobInstance: " + jobExecution.getJobInstance());
                    log.debug("Job getStepExecutions: " + jobExecution.getStepExecutions());
                    log.debug("Job getLastUpdated: " + jobExecution.getLastUpdated());
                    log.debug("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
                } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                    // 실패한 경우의 로직
                    log.debug("jobExecution 이 실패한 경우");
                }
            } catch (Exception e) {
                // 예외 처리 로직
                log.debug("jobExecution 이 실패했습니다");
                log.error(e.getMessage());
            }
        }


    }
}