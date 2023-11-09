package com.world.alfs.controller.event_batch;

import com.world.alfs.controller.event_batch.request.JobLaunchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
@Slf4j
public class RestBatchController {

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;
    private final JobExplorer jobExplorer;
//
//    @PostMapping("/test")
//    public ExitStatus runJob(@RequestBody JobLaunchRequest request) throws Exception {
//        // 잡 이름의 빈 검색
//        Job job = jobRegistry.getJob(request.getName());
//
//        // job 파라미터 추출
//        // builder의 인자 -> (현재 만드는 잡 파라미터에 추가하고 싶은 잡파라미터, jobExplorer)
//        // jobExplorer가 필요한 이유는 다음 파라미터를 위해 기존 run.id를 가져오기 위해서 필요함
//        JobParameters jobParameters = new JobParametersBuilder(request.getJobParameters(),jobExplorer)
//                .getNextJobParameters(job)
//                .toJobParameters();
//
//        // 잡 실행하고 ExitStatus 반환
//        return this.jobLauncher.run(job, jobParameters).getExitStatus();
////        return this.jobLauncher.run(job).getExitStatus();
//    }

}
