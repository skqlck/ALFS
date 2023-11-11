package com.world.alfs.config.batch.special;

import com.world.alfs.domain.special.Special;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.HashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final JobCompletionNotificationListener jobCompletionNotificationListener;

    private static final String JOB_NAME = "specialStartJob";
    private static final String STEP_NAME = "specialStartStep";
    private static final int CHUNK_SIZE = 3;

    @Bean
    public Job specialStartJob(){
        // log.info("specialStartJob");
        return jobBuilderFactory.get(JOB_NAME)
                .start(specialStartStep())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
//                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Step specialStartStep(){
        // log.info("specialStartStep");
        return stepBuilderFactory.get(STEP_NAME)
                .<Special, Special>chunk(CHUNK_SIZE)
                .reader(customItemReader(0L, 0L))
                .processor(customItemProcessor())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaCursorItemReader<Special> customItemReader(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['productId']}") Long productId) {
        // log.info("customItemReader");
//        log.debug("jobParameter 확인: "+supervisorId+" "+productId);
        if (supervisorId.equals(0L) || productId.equals(0L)) {
            throw new IllegalArgumentException("supervisorId와 productId는 필수 파라미터입니다.");
        }

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("supervisorId", supervisorId);
        parameters.put("productId", productId);
        parameters.put("status",0);

        String queryString = "select s from Special s where s.supervisor.id = :supervisorId and s.status = :status and s.product.id = :productId";
//        log.debug("쿼리 확인: " + queryString);

        return new JpaCursorItemReaderBuilder<Special>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(queryString)
                .parameterValues(parameters)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Special, Special> customItemProcessor() {
        // log.info("customItemProcessor");
        return new CustomJpaItemProcessor(entityManagerFactory);
    }

    @Bean
    @StepScope
    @Transactional
    public JpaItemWriter<Special> customItemWriter() {
        // log.info("customItemWriter");
        JpaItemWriter<Special> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }

}
