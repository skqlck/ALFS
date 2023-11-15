package com.world.alfs.config.batch.special;

import com.world.alfs.domain.event.Event;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String JOB_START_NAME = "specialStartJob";
    private static final String START_STEP_NAME = "specialStartStep";

    private static final String JOB_END_NAME = "specialStartJob";
    private static final String END_STEP_NAME = "specialStartStep";
    private static final int CHUNK_SIZE = 3;

    @Bean
    public Job specialStartJob(){
        // log.info("specialStartJob");
        return jobBuilderFactory.get(JOB_START_NAME)
                .start(specialStartStep())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Job specialEndJob(){
        // log.info("specialStartJob");
        return jobBuilderFactory.get(JOB_END_NAME)
                .start(specialEndStep())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
//                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Job specialStartJob2(){
        // log.info("specialStartJob");
        return jobBuilderFactory.get(JOB_START_NAME)
                .start(specialStartStep2())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Job specialEndJob2(){
         log.info("specialEndJob2");
        return jobBuilderFactory.get(JOB_END_NAME)
                .start(specialEndStep2())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
                .listener(jobCompletionNotificationListener)
                .build();
    }


    @Bean
    public Step specialStartStep(){
        // log.info("specialStartStep");
        return stepBuilderFactory.get(START_STEP_NAME)
                .<Special, Special>chunk(CHUNK_SIZE)
                .reader(customStartItemReader(0L, 0L))
                .processor(customStartItemProcessor())
                .writer(customStartItemWriter())
                .build();
    }

    @Bean
    public Step specialEndStep(){
        // log.info("specialStartStep");
        return stepBuilderFactory.get(END_STEP_NAME)
                .<Special, Special>chunk(CHUNK_SIZE)
                .reader(customEndItemReader(0L, 0L))
                .processor(customEndItemProcessor())
                .writer(customEndItemWriter())
                .build();
    }

    @Bean
    public Step specialStartStep2(){
        // log.info("specialStartStep");
        return stepBuilderFactory.get(START_STEP_NAME)
                .<Event, Event>chunk(CHUNK_SIZE)
                .reader(customStartItemReader2(0L,0L))
                .processor(customStartItemProcessor2())
                .writer(customStartItemWriter2())
                .build();
    }

    @Bean
    public Step specialEndStep2(){
         log.info("specialEndStep2");
        return stepBuilderFactory.get(END_STEP_NAME)
                .<Event, Event>chunk(CHUNK_SIZE)
                .reader(customEndItemReader2(0L, 0L))
                .processor(customEndItemProcessor2())
                .writer(customEndItemWriter2())
                .build();
    }
//
    @Bean
    @StepScope
    public JpaCursorItemReader<Special> customStartItemReader(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['productId']}") Long productId) {
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
    public JpaCursorItemReader<Event> customStartItemReader2(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['eventId']}") Long eventId) {
        // log.info("customItemReader");
//        log.debug("jobParameter 확인: "+supervisorId+" "+productId);
        if (supervisorId.equals(0L) || eventId.equals(0L)) {
            throw new IllegalArgumentException("supervisorId와 productId는 필수 파라미터입니다.");
        }

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("supervisorId", supervisorId);
        parameters.put("eventId", eventId);
        parameters.put("status",0);

        String queryString = "select s from Event s where s.supervisor.id = :supervisorId and s.status = :status and s.id = :eventId";
//        log.debug("쿼리 확인: " + queryString);

        return new JpaCursorItemReaderBuilder<Event>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(queryString)
                .parameterValues(parameters)
                .build();
    }

    @Bean
    @StepScope
    public JpaCursorItemReader<Special> customEndItemReader(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['productId']}") Long productId) {
        // log.info("customItemReader");
//        log.debug("jobParameter 확인: "+supervisorId+" "+productId);
        if (supervisorId.equals(0L) || productId.equals(0L)) {
            throw new IllegalArgumentException("supervisorId와 productId는 필수 파라미터입니다.");
        }

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("supervisorId", supervisorId);
        parameters.put("productId", productId);
        parameters.put("status",1);

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
    public JpaCursorItemReader<Event> customEndItemReader2(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['eventId']}") Long eventId) {
         log.info("customEndItemReader2");
//        log.debug("jobParameter 확인: "+supervisorId+" "+productId);
        if (supervisorId.equals(0L) || eventId.equals(0L)) {
            throw new IllegalArgumentException("supervisorId와 productId는 필수 파라미터입니다.");
        }

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("supervisorId", supervisorId);
        parameters.put("eventId", eventId);
        parameters.put("status",1);

        String queryString = "select s from Event s where s.supervisor.id = :supervisorId and s.status = :status and s.id = :eventId";
        log.debug("쿼리 확인: " + queryString);

        return new JpaCursorItemReaderBuilder<Event>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString(queryString)
                .parameterValues(parameters)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Special, Special> customStartItemProcessor() {
        // log.info("customItemProcessor");
        return new CustomJpaStartItemProcessor(entityManagerFactory);
    }

    @Bean
    @StepScope
    public ItemProcessor<Special, Special> customEndItemProcessor() {
        // log.info("customItemProcessor");
        return new CustomJpaEndItemProcessor(entityManagerFactory);
    }

    @Bean
    @StepScope
    public ItemProcessor<Event, Event> customStartItemProcessor2() {
        // log.info("customItemProcessor");
        return new CustomJpaStartItemProcessor2(entityManagerFactory);
    }

    @Bean
    @StepScope
    public ItemProcessor<Event, Event> customEndItemProcessor2() {
        // log.info("customItemProcessor");
        return new CustomJpaEndItemProcessor2(entityManagerFactory);
    }


    @Bean
    @StepScope
    @Transactional
    public JpaItemWriter<Special> customStartItemWriter() {
        // log.info("customItemWriter");
        JpaItemWriter<Special> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }

    @Bean
    @StepScope
    @Transactional
    public JpaItemWriter<Event> customStartItemWriter2() {
        // log.info("customItemWriter");
        JpaItemWriter<Event> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }

    @Bean
    @StepScope
    @Transactional
    public JpaItemWriter<Special> customEndItemWriter() {
        // log.info("customItemWriter");
        JpaItemWriter<Special> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }

    @Bean
    @StepScope
    @Transactional
    public JpaItemWriter<Event> customEndItemWriter2() {
        // log.info("customItemWriter");
        JpaItemWriter<Event> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }

}


