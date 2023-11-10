package com.world.alfs.config.batch.special;

import com.world.alfs.domain.special.Special;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
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
        return jobBuilderFactory.get(JOB_NAME)
                .start(specialStartStep())
                .incrementer(new RunIdIncrementer()) // listener 추가 가능
                .listener(jobCompletionNotificationListener)
                .build();
    }

    @Bean
    public Step specialStartStep(){
        return stepBuilderFactory.get(STEP_NAME)
                .<Special, Special>chunk(CHUNK_SIZE)
                .reader(customItemReader(0L, 0L))
                .processor(customItemProcessor())
                .writer(customItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Special> customItemReader(@Value("#{jobParameters['supervisorId']}") Long supervisorId, @Value("#{jobParameters['productId']}") Long productId) {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("id", supervisorId);
        parameters.put("productId", productId);
        parameters.put("status",0);

        return new JpaCursorItemReaderBuilder<Special>()
                .name("jpaCursorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select s from Special s where supervisor_id =:id and status =:status and product_id =: productId")
                .parameterValues(parameters)
                .build();
    }

    @Bean
    public ItemProcessor<Special, Special> customItemProcessor() {
        return new CustomItemProcessor();
    }

    public static class CustomItemProcessor implements ItemProcessor<Special, Special> {
        @Override
        public Special process(Special item) {
            item.setStatus(1); // Special 객체의 status를 0에서 1로 변경
            return item;
        }
    }

    @Bean
    @Transactional
    public ItemWriter<Special> customItemWriter() {
        JpaItemWriter<Special> itemWriter = new JpaItemWriter<>();
        itemWriter.setEntityManagerFactory(entityManagerFactory);
        return itemWriter;
    }
}
