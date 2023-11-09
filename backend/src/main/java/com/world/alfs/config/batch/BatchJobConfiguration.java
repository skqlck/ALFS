package com.world.alfs.config.batch;

import com.world.alfs.domain.special.Special;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final String JOB_NAME = "jpaJob";
    private static final String STEP_NAME = "jpaStep";
    private static final int CHUNK_SIZE = 3;

    @Bean
    public Job jpaJob(){
        return jobBuilderFactory.get(JOB_NAME)
                .start(jpaStep())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
                .build();
    }

    @Bean
    public Step jpaStep(){
        return stepBuilderFactory.get(STEP_NAME)
                .<Special, Special>chunk(CHUNK_SIZE)
                .reader(customItemReader())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Special> customItemReader() {

//        HashMap<String, Object> parameters = new HashMap<>();
//        parameters.put("age",25);

        return new JpaCursorItemReaderBuilder<Special>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select s from Special s") // JPQL -> 테이블명은 첫글자만 대문자
//                .parameterValues(parameters)
                .build();
    }

//    @Bean
//    public CustomerItemProcessor jpaItemProcessor(){
//
//    }

    @Bean
    public ItemWriter<Special> customItemWriter() {
        return items -> {
            for (Special item : items) {
                System.out.println("item = " + item.toString());
            }
        };
    }
}
